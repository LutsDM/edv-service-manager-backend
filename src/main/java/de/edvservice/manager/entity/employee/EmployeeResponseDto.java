package de.edvservice.manager.entity.employee;

public record EmployeeResponseDto(
		Long id,
		String firstName,
		String lastName,
		String email,
		String phone,
		boolean active
) {
}
