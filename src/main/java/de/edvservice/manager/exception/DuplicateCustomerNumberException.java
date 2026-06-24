package de.edvservice.manager.exception;

public class DuplicateCustomerNumberException extends RuntimeException {

	public DuplicateCustomerNumberException(String customerNumber) {
		super("Customer number already exists: " + customerNumber);
	}
}
