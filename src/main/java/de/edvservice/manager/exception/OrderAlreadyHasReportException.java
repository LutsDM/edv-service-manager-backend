package de.edvservice.manager.exception;

public class OrderAlreadyHasReportException extends RuntimeException {

	public OrderAlreadyHasReportException(Long orderId) {
		super("Order " + orderId + " is already linked to a service report.");
	}
}
