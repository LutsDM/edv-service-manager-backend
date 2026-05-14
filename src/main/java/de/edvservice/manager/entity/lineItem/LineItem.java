package de.edvservice.manager.entity.lineItem;

import java.math.BigDecimal;

import de.edvservice.manager.entity.report.ServiceReport;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "line_items")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class LineItem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Setter(AccessLevel.NONE)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "service_report_id", nullable = false)
	private ServiceReport serviceReport;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "unit_price", precision = 10, scale = 2)
	private BigDecimal unitPrice;

	@Column(name = "total_price", precision = 10, scale = 2)
	private BigDecimal totalPrice;
}
