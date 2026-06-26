package de.edvservice.manager.exception;

public class DuplicateEmployeeEmailException extends RuntimeException {

	public DuplicateEmployeeEmailException(String email) {
		super("Employee with this e-mail already exists: " + email);
	}
}
