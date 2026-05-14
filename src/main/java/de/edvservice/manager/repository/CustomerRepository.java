package de.edvservice.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.edvservice.manager.entity.customer.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findByCustomerNumber(String customerNumber);

	boolean existsByCustomerNumber(String customerNumber);
}
