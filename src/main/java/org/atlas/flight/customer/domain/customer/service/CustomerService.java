package org.atlas.flight.customer.domain.customer.service;

import org.atlas.flight.customer.domain.customer.entity.Customer;

public interface CustomerService {
	Customer getCustomer(String customerId);
}
