package org.atlas.flight.customer.domain.passenger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.atlas.flight.core.ApiResponse;
import org.atlas.flight.core.annotation.CurrentUser;
import org.atlas.flight.customer.domain.passenger.dto.request.PassengerCreateRequest;
import org.atlas.flight.customer.domain.passenger.dto.response.PassengerResponse;
import org.atlas.flight.customer.domain.passenger.service.PassengerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
@RequiredArgsConstructor
@Tag(name = "탑승자")
public class PassengerController {
	private final PassengerService passengerService;

	@GetMapping
	@Operation(summary = "탑승자 목록 조회")
	public ApiResponse<List<PassengerResponse>> getPassengers(@CurrentUser String ownerCustomerId) {
		return ApiResponse.success(passengerService.getPassengers(ownerCustomerId));
	}

	@PostMapping
	@Operation(summary = "탑승자(가족) 등록")
	public ApiResponse<Void> registerPassenger(
			@CurrentUser String ownerCustomerId,
			@Valid @RequestBody PassengerCreateRequest request) {
		passengerService.registerPassenger(ownerCustomerId, request);
		return ApiResponse.success();
	}

	@DeleteMapping("/{customerNumber}")
	@Operation(summary = "탑승자 삭제")
	public ApiResponse<Void> deletePassenger(
			@CurrentUser String ownerCustomerId,
			@Parameter(description = "탑승자 회원번호") @PathVariable String customerNumber) {
		passengerService.deletePassenger(ownerCustomerId, customerNumber);
		return ApiResponse.success();
	}
}
