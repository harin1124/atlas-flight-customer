package org.atlas.flight.customer.domain.passenger.service;

import org.atlas.flight.customer.domain.passenger.dto.request.PassengerCreateRequest;
import org.atlas.flight.customer.domain.passenger.dto.response.PassengerResponse;

import java.util.List;

public interface PassengerService {

	/** 소유자의 탑승자 목록 조회 (본인 + 등록 가족). */
	List<PassengerResponse> getPassengers(String ownerCustomerId);

	/** 가족(회원) 탑승자 등록. 본인확인을 거친다. */
	void registerPassenger(String ownerCustomerId, PassengerCreateRequest request);

	/** 탑승자 삭제 (회원번호로 지정, 본인 행은 삭제 불가). */
	void deletePassenger(String ownerCustomerId, String customerNumber);
}
