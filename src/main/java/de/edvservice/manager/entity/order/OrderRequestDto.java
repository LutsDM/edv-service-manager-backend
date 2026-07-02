package de.edvservice.manager.entity.order;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import de.edvservice.manager.validation.ValidSignatureData;

public record OrderRequestDto(
		@NotBlank(message = "Order number can't be empty.")
		String orderNumber,
		@NotNull(message = "Customer is required.")
		Long customerId,
		@NotNull(message = "Work date is required.")
		LocalDate workDate,
		@NotNull(message = "Hourly rate is required.")
		BigDecimal hourlyRate,
		Integer employeeCount,
		Long employeeId,
		String orderDetails,
		@ValidSignatureData
		String customerSignatureData,
		@ValidSignatureData
		String employeeSignatureData
) {
}
