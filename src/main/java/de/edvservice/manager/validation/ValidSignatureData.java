package de.edvservice.manager.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = SignatureDataValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSignatureData {

	String message() default "Invalid signature data. Expected PNG data URL: data:image/png;base64,...";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
