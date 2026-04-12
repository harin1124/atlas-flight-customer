package org.atlas.flight.customer.domain.customer.service.impl;

import lombok.RequiredArgsConstructor;
import org.atlas.flight.core.exception.ApiException;
import org.atlas.flight.core.message.ResponseCodeGeneral;
import org.atlas.flight.customer.domain.customer.entity.Customer;
import org.atlas.flight.customer.domain.customer.repository.CustomerRepository;
import org.atlas.flight.customer.domain.customer.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerImpl implements CustomerService {
	private final CustomerRepository customerRepository;
	
	@Override
	public Customer getCustomer(String customerId) {
		return customerRepository
				.findByCustomerId(customerId)
				.orElseThrow(() -> new ApiException(ResponseCodeGeneral.NOT_FOUND));
	}
}
