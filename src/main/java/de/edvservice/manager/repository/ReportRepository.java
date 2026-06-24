package de.edvservice.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.edvservice.manager.entity.order.Order;
import de.edvservice.manager.entity.report.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

	Optional<Report> findByOrder(Order order);

	Optional<Report> findByOrder_Id(Long orderId);

	boolean existsByOrder_Id(Long orderId);
}
