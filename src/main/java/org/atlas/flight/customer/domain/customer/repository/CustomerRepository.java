package org.atlas.flight.customer.domain.customer.repository;

import org.atlas.flight.customer.domain.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	Optional<Customer> findByCustomerId(String customerId);
}
