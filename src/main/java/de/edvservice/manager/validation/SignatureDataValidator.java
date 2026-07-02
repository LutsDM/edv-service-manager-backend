package de.edvservice.manager.validation;

import java.util.regex.Pattern;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SignatureDataValidator implements ConstraintValidator<ValidSignatureData, String> {

	static final int MAX_SIGNATURE_LENGTH = 1_048_576;

	private static final Pattern SIGNATURE_PATTERN = Pattern.compile(
			"^data:image/png;base64,[A-Za-z0-9+/]+=*$");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || value.isBlank()) {
			return true;
		}
		if (value.length() > MAX_SIGNATURE_LENGTH) {
			return false;
		}
		return SIGNATURE_PATTERN.matcher(value).matches();
	}
}
