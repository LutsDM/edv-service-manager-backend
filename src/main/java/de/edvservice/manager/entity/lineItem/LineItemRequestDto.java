package de.edvservice.manager.entity.lineItem;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LineItemRequestDto(
		@NotBlank(message = "Title can't be empty.")
		String title,
		@NotNull(message = "Quantity is required.")
		Integer quantity,
		BigDecimal unitPrice,
		BigDecimal totalPrice
) {
}
