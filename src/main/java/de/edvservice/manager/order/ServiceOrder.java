package de.edvservice.manager.order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import de.edvservice.manager.customer.Customer;
import de.edvservice.manager.employee.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "service_orders")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class ServiceOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Setter(AccessLevel.NONE)
	@Column(name = "id")
	private Long id;

	@Column(name = "order_number", nullable = false, unique = true, length = 64)
	private String orderNumber;

	@NotNull(message = "Customer is required.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@NotNull(message = "Work date is required.")
	@Column(name = "work_date", nullable = false)
	private LocalDate workDate;

	@NotNull(message = "Hourly rate is required.")
	@Column(name = "hourly_rate", nullable = false, precision = 10, scale = 2)
	private BigDecimal hourlyRate;

	@Column(name = "employee_count", nullable = false)
	private Integer employeeCount = 1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@Lob
	@Column(name = "order_details")
	private String orderDetails;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, length = 32)
	private OrderStatus status = OrderStatus.DRAFT;

	@Lob
	@Column(name = "customer_signature_svg")
	private String customerSignatureSvg;

	@Lob
	@Column(name = "employee_signature_svg")
	private String employeeSignatureSvg;

	@Column(name = "customer_signed_at")
	private LocalDateTime customerSignedAt;

	@Column(name = "employee_signed_at")
	private LocalDateTime employeeSignedAt;

	@Column(name = "customer_number_snapshot", length = 64)
	private String customerNumberSnapshot;

	@Column(name = "customer_type_snapshot", length = 32)
	private String customerTypeSnapshot;

	@Column(name = "company_name_snapshot", length = 255)
	private String companyNameSnapshot;

	@Column(name = "first_name_snapshot", length = 128)
	private String firstNameSnapshot;

	@Column(name = "last_name_snapshot", length = 128)
	private String lastNameSnapshot;

	@Column(name = "phone_snapshot", length = 64)
	private String phoneSnapshot;

	@Column(name = "mobile_snapshot", length = 64)
	private String mobileSnapshot;

	@Column(name = "email_snapshot", length = 255)
	private String emailSnapshot;

	@Column(name = "street_snapshot", length = 255)
	private String streetSnapshot;

	@Column(name = "house_number_snapshot", length = 32)
	private String houseNumberSnapshot;

	@Column(name = "zip_code_snapshot", length = 32)
	private String zipCodeSnapshot;

	@Column(name = "city_snapshot", length = 128)
	private String citySnapshot;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}
