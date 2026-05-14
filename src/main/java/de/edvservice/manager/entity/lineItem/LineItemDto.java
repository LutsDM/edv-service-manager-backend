package de.edvservice.manager.entity.lineItem;

import java.math.BigDecimal;

public record LineItemDto(
		Long id,
		Long serviceReportId,
		String title,
		Integer quantity,
		BigDecimal unitPrice,
		BigDecimal totalPrice
) {
}
