package org.atlas.flight.customer.domain.customer.repository;

import org.atlas.flight.customer.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	Optional<Customer> findByCustomerId(String customerId);

	/** 회원번호로 단건 조회 (탑승자 등록 시 대상 회원 탐색). */
	Optional<Customer> findByCustomerNumber(String customerNumber);

	/** 고객 아이디 다건 조회 (탑승자 목록 신원 조인). */
	List<Customer> findByCustomerIdIn(Collection<String> customerIds);
}
