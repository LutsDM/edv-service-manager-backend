package de.edvservice.manager.entity.report;

import java.math.BigDecimal;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;

import de.edvservice.manager.validation.ValidSignatureData;

public record ReportRequestDto(
		Long orderId,
		String externalOrderNumber,
		boolean includeArrival,
		LocalTime arrivalStart,
		LocalTime arrivalEnd,
		boolean includeDeparture,
		LocalTime departureStart,
		LocalTime departureEnd,
		@NotNull(message = "Work start is required.")
		LocalTime workStart,
		@NotNull(message = "Work end is required.")
		LocalTime workEnd,
		Integer arrivalMinutes,
		Integer workMinutes,
		Integer departureMinutes,
		Integer totalMinutes,
		BigDecimal netAmount,
		BigDecimal taxRate,
		BigDecimal taxAmount,
		BigDecimal extraPositionsAmount,
		BigDecimal grossAmount,
		String workDescription,
		@ValidSignatureData
		String customerSignatureData,
		@ValidSignatureData
		String employeeSignatureData
) {
}
