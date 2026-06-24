package de.edvservice.manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.edvservice.manager.entity.lineItem.LineItemResponseDto;
import de.edvservice.manager.entity.report.ReportResponseDto;
import de.edvservice.manager.entity.report.mapper.ReportMapper;
import de.edvservice.manager.repository.LineItemRepository;
import de.edvservice.manager.repository.ReportRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportService {

	private final ReportRepository reportRepository;
	private final LineItemRepository lineItemRepository;
	private final ReportMapper reportMapper;

	public Optional<ReportResponseDto> findById(Long id) {
		return reportRepository.findById(id).map(reportMapper::toResponseDto);
	}

	public Optional<ReportResponseDto> findByOrderId(Long orderId) {
		return reportRepository.findByOrder_Id(orderId).map(reportMapper::toResponseDto);
	}

	public Page<ReportResponseDto> findAll(Pageable pageable) {
		return reportRepository.findAll(pageable).map(reportMapper::toResponseDto);
	}

	public List<LineItemResponseDto> findLineItemsByReportId(Long reportId) {
		return lineItemRepository.findByReport_IdOrderByIdAsc(reportId).stream()
				.map(reportMapper::toResponseDto)
				.toList();
	}
}
