package de.edvservice.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.edvservice.manager.entity.order.ServiceOrder;

@Repository
public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

	Optional<ServiceOrder> findByOrderNumber(String orderNumber);

	boolean existsByOrderNumber(String orderNumber);
}
