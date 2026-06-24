package de.edvservice.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import de.edvservice.manager.entity.lineItem.LineItem;
import de.edvservice.manager.entity.report.Report;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {

	List<LineItem> findByReportOrderByIdAsc(Report report);

	List<LineItem> findByReport_IdOrderByIdAsc(Long reportId);

	void deleteByReport_Id(Long reportId);
}
