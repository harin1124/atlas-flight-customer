package org.atlas.flight.customer.domain.passenger.service.impl;

import lombok.RequiredArgsConstructor;
import org.atlas.flight.core.exception.ApiException;
import org.atlas.flight.core.message.ResponseCodeGeneral;
import org.atlas.flight.customer.domain.customer.entity.Customer;
import org.atlas.flight.customer.domain.customer.repository.CustomerRepository;
import org.atlas.flight.customer.domain.passenger.dto.request.PassengerCreateRequest;
import org.atlas.flight.customer.domain.passenger.dto.response.PassengerResponse;
import org.atlas.flight.customer.domain.passenger.entity.Passenger;
import org.atlas.flight.customer.domain.passenger.entity.RelCd;
import org.atlas.flight.customer.domain.passenger.mapper.PassengerMapper;
import org.atlas.flight.customer.domain.passenger.repository.PassengerRepository;
import org.atlas.flight.customer.domain.passenger.service.PassengerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassengerImpl implements PassengerService {

	private static final String NOT_DELETED = "N";

	private final PassengerRepository passengerRepository;
	private final CustomerRepository customerRepository;
	private final PassengerMapper passengerMapper;

	@Override
	@Transactional(readOnly = true)
	public List<PassengerResponse> getPassengers(String ownerCustomerId) {
		List<Passenger> passengers =
				passengerRepository.findByOwnerCustomerIdAndDelYnOrderByRegDtAsc(ownerCustomerId, NOT_DELETED);
		if (passengers.isEmpty()) {
			return List.of();
		}

		List<String> personIds = passengers.stream()
				.map(Passenger::getPassengerCustomerId)
				.distinct()
				.toList();
		Map<String, Customer> personById = customerRepository.findByCustomerIdIn(personIds).stream()
				.collect(Collectors.toMap(Customer::getCustomerId, Function.identity()));

		return passengers.stream()
				.map(p -> {
					Customer person = personById.get(p.getPassengerCustomerId());
					if (person == null) {
						// 연결 회원이 사라진 비정상 데이터 → 목록에서 제외
						return null;
					}
					return passengerMapper.toResponse(p, person);
				})
				.filter(Objects::nonNull)
				.toList();
	}

	@Override
	@Transactional
	public void registerPassenger(String ownerCustomerId, PassengerCreateRequest request) {
		if (request.getRelationCd() == RelCd.SELF) {
			// 본인은 가입 시 자동등록만 허용
			throw new ApiException(ResponseCodeGeneral.BAD_REQUEST);
		}

		Customer person = customerRepository.findByCustomerNumber(request.getCustomerNumber())
				.orElseThrow(() -> new ApiException(ResponseCodeGeneral.NOT_FOUND));

		// 탈퇴/삭제 회원은 등록 불가
		if ("Y".equals(person.getWithdrawalYn()) || "Y".equals(person.getDelYn())) {
			throw new ApiException(ResponseCodeGeneral.BAD_REQUEST);
		}

		// 본인확인: 회원번호 + 이름 + 생년월일 일치
		boolean identityMatches =
				person.getKorFirstName().equals(request.getKorFirstName())
						&& person.getKorLastName().equals(request.getKorLastName())
						&& person.getBirthday().equals(request.getBirthday());
		if (!identityMatches) {
			throw new ApiException(ResponseCodeGeneral.BAD_REQUEST);
		}

		// 본인은 자동등록 대상 → 가족으로 재등록 불가
		if (ownerCustomerId.equals(person.getCustomerId())) {
			throw new ApiException(ResponseCodeGeneral.BAD_REQUEST);
		}

		// 같은 방향(복합키) 기존 행 처리: 살아있으면 중복, 삭제됐으면 되살림
		Optional<Passenger> existing = passengerRepository
				.findByOwnerCustomerIdAndPassengerCustomerId(ownerCustomerId, person.getCustomerId());
		if (existing.isPresent()) {
			Passenger row = existing.get();
			if (NOT_DELETED.equals(row.getDelYn())) {
				throw new ApiException(ResponseCodeGeneral.BAD_REQUEST);
			}
			row.reactivate(request.getRelationCd());
			return;
		}

		Passenger passenger = Passenger.createNew(
				ownerCustomerId,
				person.getCustomerId(),
				request.getRelationCd()
		);
		passengerRepository.save(passenger);
	}

	@Override
	@Transactional
	public void deletePassenger(String ownerCustomerId, String customerNumber) {
		Customer person = customerRepository.findByCustomerNumber(customerNumber)
				.orElseThrow(() -> new ApiException(ResponseCodeGeneral.NOT_FOUND));

		Passenger passenger = passengerRepository
				.findByOwnerCustomerIdAndPassengerCustomerId(ownerCustomerId, person.getCustomerId())
				.orElseThrow(() -> new ApiException(ResponseCodeGeneral.NOT_FOUND));

		if (!NOT_DELETED.equals(passenger.getDelYn())) {
			throw new ApiException(ResponseCodeGeneral.NOT_FOUND);
		}
		if (passenger.isSelf()) {
			// 본인 행은 삭제 불가
			throw new ApiException(ResponseCodeGeneral.BAD_REQUEST);
		}
		passenger.markDeleted();
	}
}
