package org.atlas.flight.customer.domain.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.atlas.flight.core.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.atlas.flight.customer.domain.customer.entity.Customer;
import org.atlas.flight.customer.domain.customer.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "고객")
public class CustomerController {
	private final CustomerService customerService;
	
	@GetMapping("/{id}")
	@Operation(summary = "고객 조회")
	public ApiResponse<Customer> getCustomer(@PathVariable("id") String userId) {
		System.out.println("성공");
		return ApiResponse.success(customerService.getCustomer(userId));
	}
}