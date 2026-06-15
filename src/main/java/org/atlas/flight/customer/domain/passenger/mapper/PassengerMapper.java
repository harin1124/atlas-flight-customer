package org.atlas.flight.customer.domain.passenger.mapper;

import org.atlas.flight.customer.domain.customer.entity.Customer;
import org.atlas.flight.customer.domain.passenger.dto.response.PassengerResponse;
import org.atlas.flight.customer.domain.passenger.entity.Passenger;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper {

	/**
	 * 탑승자 링크 + 연결된 회원 신원을 합쳐 응답으로 변환한다.
	 */
	public PassengerResponse toResponse(Passenger passenger, Customer person) {
		return PassengerResponse.builder()
				.relationCd(passenger.getRelCd())
				.customerNumber(person.getCustomerNumber())
				.korFirstName(person.getKorFirstName())
				.korLastName(person.getKorLastName())
				.engFirstName(person.getEngFirstName())
				.engLastName(person.getEngLastName())
				.genderCd(person.getGenderCd())
				.birthday(person.getBirthday())
				.build();
	}
}
