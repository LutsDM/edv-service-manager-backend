package de.edvservice.manager.entity.customer;

import java.time.LocalDateTime;

public record CustomerDto(
		Long id,
		String customerNumber,
		CustomerType type,
		String companyName,
		String firstName,
		String lastName,
		String phone,
		String mobile,
		String email,
		String street,
		String houseNumber,
		String zipCode,
		String city,
		LocalDateTime createdAt,
		LocalDateTime updatedAt
) {
}
