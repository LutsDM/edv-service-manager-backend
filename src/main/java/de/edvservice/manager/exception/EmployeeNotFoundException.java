package de.edvservice.manager.exception;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(Long id) {
		super("Employee not found: id=" + id);
	}
}
