package de.edvservice.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.edvservice.manager.entity.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findByOrderNumber(String orderNumber);

	boolean existsByOrderNumber(String orderNumber);

	boolean existsByCustomer_Id(Long customerId);

	boolean existsByEmployee_Id(Long employeeId);

	boolean existsByOrderNumberAndIdNot(String orderNumber, Long id);
}
