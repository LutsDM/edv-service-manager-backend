package de.edvservice.manager.entity.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeRequestDto(
		@NotBlank(message = "First name can't be empty.")
		String firstName,
		@NotBlank(message = "Last name can't be empty.")
		String lastName,
		@Email(message = "Invalid e-mail format.")
		@NotBlank(message = "E-mail can't be empty.")
		String email,
		String phone,
		boolean active
) {
}
