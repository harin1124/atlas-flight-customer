package org.atlas.flight.customer.domain.customer.mapper;

import org.atlas.flight.customer.domain.customer.dto.request.CustomerCreateRequest;
import org.atlas.flight.customer.domain.customer.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
	public Customer toEntity(CustomerCreateRequest request, String customerNumber) {
		return Customer.createNew(
				request.getCustomerId(),
				customerNumber,
				request.getKorFirstName(),
				request.getKorLastName(),
				request.getEngFirstName(),
				request.getEngLastName(),
				request.getBirthday(),
				request.getGenderCd(),
				request.getPhoneCountryCd(),
				request.getPhoneNumber(),
				request.getEmail(),
				request.getPreferredLangCd()
		);
	}
}
