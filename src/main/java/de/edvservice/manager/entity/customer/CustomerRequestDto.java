package de.edvservice.manager.entity.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestDto(
		@NotBlank(message = "Customer number can't be empty.")
		String customerNumber,
		@NotNull(message = "Customer type is required.")
		CustomerType type,
		String companyName,
		@NotBlank(message = "First name can't be empty.")
		String firstName,
		@NotBlank(message = "Last name can't be empty.")
		String lastName,
		String phone,
		String mobile,
		@Email(message = "Invalid e-mail format.")
		String email,
		String street,
		String houseNumber,
		String zipCode,
		String city
) {
}
