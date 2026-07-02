package de.edvservice.manager.exception;

public class SignatureModificationException extends RuntimeException {

	public SignatureModificationException() {
		super("Signature cannot be modified on a signed document.");
	}
}
