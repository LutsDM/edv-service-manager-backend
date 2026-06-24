package de.edvservice.manager.exception;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(Long id) {
		super("Customer not found: id=" + id);
	}
}
