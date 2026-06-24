package de.edvservice.manager.entity.report;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import de.edvservice.manager.entity.order.Order;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reports")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(AccessLevel.NONE)
	@Column(name = "id")
	private Long id;

	@NotNull(message = "Order is required.")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id", nullable = false, unique = true)
	private Order order;

	@Column(name = "include_arrival", nullable = false)
	private boolean includeArrival = false;

	@Column(name = "arrival_start")
	private LocalTime arrivalStart;

	@Column(name = "arrival_end")
	private LocalTime arrivalEnd;

	@Column(name = "include_departure", nullable = false)
	private boolean includeDeparture = false;

	@Column(name = "departure_start")
	private LocalTime departureStart;

	@Column(name = "departure_end")
	private LocalTime departureEnd;

	@NotNull(message = "Work start is required.")
	@Column(name = "work_start", nullable = false)
	private LocalTime workStart;

	@NotNull(message = "Work end is required.")
	@Column(name = "work_end", nullable = false)
	private LocalTime workEnd;

	@Column(name = "arrival_minutes", nullable = false)
	private Integer arrivalMinutes = 0;

	@Column(name = "work_minutes", nullable = false)
	private Integer workMinutes;

	@Column(name = "departure_minutes", nullable = false)
	private Integer departureMinutes = 0;

	@Column(name = "total_minutes", nullable = false)
	private Integer totalMinutes;

	@Column(name = "net_amount", precision = 10, scale = 2)
	private BigDecimal netAmount;

	@Column(name = "tax_rate", precision = 5, scale = 2)
	private BigDecimal taxRate;

	@Column(name = "tax_amount", precision = 10, scale = 2)
	private BigDecimal taxAmount;

	@Column(name = "extra_positions_amount", precision = 10, scale = 2)
	private BigDecimal extraPositionsAmount;

	@Column(name = "gross_amount", precision = 10, scale = 2)
	private BigDecimal grossAmount;

	@Column(name = "work_description", columnDefinition = "TEXT")
	private String workDescription;

	@Column(name = "customer_signature_data", columnDefinition = "TEXT")
	private String customerSignatureData;

	@Column(name = "employee_signature_data", columnDefinition = "TEXT")
	private String employeeSignatureData;

	@Column(name = "customer_signed_at")
	private LocalDateTime customerSignedAt;

	@Column(name = "employee_signed_at")
	private LocalDateTime employeeSignedAt;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 32)
	private ReportStatus status = ReportStatus.DRAFT;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}
