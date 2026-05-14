package de.edvservice.manager.entity.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ServiceOrderDto(
		Long id,
		String orderNumber,
		Long customerId,
		Long employeeId,
		LocalDate workDate,
		BigDecimal hourlyRate,
		Integer employeeCount,
		String orderDetails,
		OrderStatus status,
		String customerSignatureSvg,
		String employeeSignatureSvg,
		LocalDateTime customerSignedAt,
		LocalDateTime employeeSignedAt,
		String customerNumberSnapshot,
		String customerTypeSnapshot,
		String companyNameSnapshot,
		String firstNameSnapshot,
		String lastNameSnapshot,
		String phoneSnapshot,
		String mobileSnapshot,
		String emailSnapshot,
		String streetSnapshot,
		String houseNumberSnapshot,
		String zipCodeSnapshot,
		String citySnapshot,
		LocalDateTime createdAt,
		LocalDateTime updatedAt
) {
}
