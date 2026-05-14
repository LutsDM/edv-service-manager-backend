package de.edvservice.manager.entity.employee;

public record EmployeeDto(
		Long id,
		String firstName,
		String lastName,
		String email,
		String phone,
		boolean active
) {
}
