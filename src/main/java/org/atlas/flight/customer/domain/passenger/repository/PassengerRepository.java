package org.atlas.flight.customer.domain.passenger.repository;

import org.atlas.flight.customer.domain.passenger.entity.Passenger;
import org.atlas.flight.customer.domain.passenger.entity.PassengerId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PassengerRepository extends JpaRepository<Passenger, PassengerId> {

	/** 소유자의 탑승자 목록 (미삭제). */
	List<Passenger> findByOwnerCustomerIdAndDelYnOrderByRegDtAsc(String ownerCustomerId, String delYn);

	/**
	 * 같은 방향(소유자 → 탑승자) 기존 행. 삭제 여부와 무관하게 조회한다.
	 * 복합키와 동일한 키이므로 최대 1건이다. 등록/되살리기/삭제 공용.
	 */
	Optional<Passenger> findByOwnerCustomerIdAndPassengerCustomerId(String ownerCustomerId, String passengerCustomerId);
}
