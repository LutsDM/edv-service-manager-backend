package de.edvservice.manager.entity.report;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ReportResponseDto(
		Long id,
		Long orderId,
		boolean includeArrival,
		LocalTime arrivalStart,
		LocalTime arrivalEnd,
		boolean includeDeparture,
		LocalTime departureStart,
		LocalTime departureEnd,
		LocalTime workStart,
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
		String customerSignatureData,
		String employeeSignatureData,
		LocalDateTime customerSignedAt,
		LocalDateTime employeeSignedAt,
		ReportStatus status,
		LocalDateTime createdAt,
		LocalDateTime updatedAt
) {
}
