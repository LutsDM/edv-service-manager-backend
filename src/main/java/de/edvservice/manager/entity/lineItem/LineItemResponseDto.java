package de.edvservice.manager.entity.lineItem;

import java.math.BigDecimal;

public record LineItemResponseDto(
		Long id,
		Long reportId,
		String title,
		Integer quantity,
		BigDecimal unitPrice,
		BigDecimal totalPrice
) {
}
