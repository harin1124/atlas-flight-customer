package org.atlas.flight.customer.domain.customer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.atlas.flight.core.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.atlas.flight.customer.domain.customer.dto.request.CustomerCreateRequest;
import org.atlas.flight.customer.domain.customer.entity.Customer;
import org.atlas.flight.customer.domain.customer.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@Tag(name = "고객")
public class CustomerController {
	private final CustomerService customerService;
	
	@GetMapping("/{customerId}")
	@Operation(summary = "고객 조회")
	public ApiResponse<Customer> getCustomer(@PathVariable String customerId) {
		return ApiResponse.success(customerService.getCustomer(customerId));
	}

	@PostMapping
	@Operation(summary = "고객 등록")
	public ApiResponse<Void> createCustomer(@Valid @RequestBody CustomerCreateRequest request) {
		System.out.println("request ::: " + request);
		customerService.createCustomer(request);
		return ApiResponse.success();
	}
}