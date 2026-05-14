package de.edvservice.manager.customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers", uniqueConstraints = {
		@UniqueConstraint(name = "uk_customers_customer_number", columnNames = "customer_number")
})
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Setter(AccessLevel.NONE)
	@Column(name = "id")
	private Long id;

	@NotBlank(message = "Customer number can't be empty.")
	@Column(name = "customer_number", nullable = false, length = 64)
	private String customerNumber;

	@NotNull(message = "Customer type is required.")
	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false, length = 32)
	private CustomerType type;

	@Column(name = "company_name", length = 255)
	private String companyName;

	@NotBlank(message = "First name can't be empty.")
	@Column(name = "first_name", nullable = false, length = 128)
	private String firstName;

	@NotBlank(message = "Last name can't be empty.")
	@Column(name = "last_name", nullable = false, length = 128)
	private String lastName;

	@Column(name = "phone", length = 64)
	private String phone;

	@Column(name = "mobile", length = 64)
	private String mobile;

	@Email(message = "Invalid e-mail format.")
	@Column(name = "email", length = 255)
	private String email;

	@Column(name = "street", length = 255)
	private String street;

	@Column(name = "house_number", length = 32)
	private String houseNumber;

	@Column(name = "zip_code", length = 32)
	private String zipCode;

	@Column(name = "city", length = 128)
	private String city;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;
}
