package org.atlas.flight.customer.domain.customer.service.impl;

import lombok.RequiredArgsConstructor;
import org.atlas.flight.core.exception.ApiException;
import org.atlas.flight.core.message.ResponseCodeGeneral;
import org.atlas.flight.customer.domain.customer.dto.request.CustomerCreateRequest;
import org.atlas.flight.customer.domain.customer.entity.Customer;
import org.atlas.flight.customer.domain.customer.mapper.CustomerMapper;
import org.atlas.flight.customer.domain.customer.issuance.CustomerNumberIssuer;
import org.atlas.flight.customer.domain.customer.repository.CustomerRepository;
import org.atlas.flight.customer.domain.customer.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomerImpl implements CustomerService {
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
	private final CustomerNumberIssuer customerNumberIssuer;
	
	@Override
	public Customer getCustomer(String customerId) {
		return customerRepository
				.findByCustomerId(customerId)
				.orElseThrow(() -> new ApiException(ResponseCodeGeneral.NOT_FOUND));
	}

	@Override
	@Transactional
	public void createCustomer(CustomerCreateRequest request) {
		if (customerRepository.existsById(request.getCustomerId())) {
			throw new ApiException(ResponseCodeGeneral.BAD_REQUEST);
		}
		String customerNumber = customerNumberIssuer.issueNext();
		Customer toSave = customerMapper.toEntity(request, customerNumber);
		customerRepository.save(toSave);
	}
}
