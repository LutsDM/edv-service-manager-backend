package de.edvservice.manager.exception;

public class EmployeeInUseException extends RuntimeException {

	public EmployeeInUseException(Long id) {
		super("Employee " + id + " is assigned to one or more orders and cannot be deleted.");
	}
}
