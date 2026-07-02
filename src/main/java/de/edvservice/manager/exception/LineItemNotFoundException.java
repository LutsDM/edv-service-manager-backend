package de.edvservice.manager.exception;

public class LineItemNotFoundException extends RuntimeException {

	public LineItemNotFoundException(Long id) {
		super("Line item not found: id=" + id);
	}
}
