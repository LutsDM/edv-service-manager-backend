package de.edvservice.manager.exception;

public class DuplicateOrderNumberException extends RuntimeException {

	public DuplicateOrderNumberException(String orderNumber) {
		super("Order with this number already exists: " + orderNumber);
	}
}
