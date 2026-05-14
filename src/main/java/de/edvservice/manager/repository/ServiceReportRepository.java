package de.edvservice.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.edvservice.manager.entity.order.ServiceOrder;
import de.edvservice.manager.entity.report.ServiceReport;

@Repository
public interface ServiceReportRepository extends JpaRepository<ServiceReport, Long> {

	Optional<ServiceReport> findByServiceOrder(ServiceOrder serviceOrder);

	Optional<ServiceReport> findByServiceOrder_Id(Long serviceOrderId);

	boolean existsByServiceOrder_Id(Long serviceOrderId);
}
