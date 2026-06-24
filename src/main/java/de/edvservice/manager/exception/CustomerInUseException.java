package de.edvservice.manager.exception;

public class CustomerInUseException extends RuntimeException {

	public CustomerInUseException(Long id) {
		super("Customer cannot be deleted because service orders exist: id=" + id);
	}
}
