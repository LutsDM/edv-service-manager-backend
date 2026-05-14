package de.edvservice.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.edvservice.manager.entity.lineItem.LineItem;
import de.edvservice.manager.entity.report.ServiceReport;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {

	List<LineItem> findByServiceReportOrderByIdAsc(ServiceReport serviceReport);

	List<LineItem> findByServiceReport_IdOrderByIdAsc(Long serviceReportId);

	void deleteByServiceReport_Id(Long serviceReportId);
}
