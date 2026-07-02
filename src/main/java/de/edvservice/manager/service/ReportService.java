package de.edvservice.manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.edvservice.manager.entity.lineItem.LineItem;
import de.edvservice.manager.entity.lineItem.LineItemRequestDto;
import de.edvservice.manager.entity.lineItem.LineItemResponseDto;
import de.edvservice.manager.entity.order.Order;
import de.edvservice.manager.entity.report.Report;
import de.edvservice.manager.entity.report.ReportRequestDto;
import de.edvservice.manager.entity.report.ReportResponseDto;
import de.edvservice.manager.entity.report.mapper.ReportMapper;
import de.edvservice.manager.exception.LineItemNotFoundException;
import de.edvservice.manager.exception.OrderAlreadyHasReportException;
import de.edvservice.manager.exception.OrderNotFoundException;
import de.edvservice.manager.exception.ReportNotFoundException;
import de.edvservice.manager.repository.LineItemRepository;
import de.edvservice.manager.repository.OrderRepository;
import de.edvservice.manager.repository.ReportRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

	private final ReportRepository reportRepository;
	private final OrderRepository orderRepository;
	private final LineItemRepository lineItemRepository;
	private final ReportMapper reportMapper;

	@Transactional(readOnly = true)
	public Optional<ReportResponseDto> findById(Long id) {
		return reportRepository.findById(id).map(reportMapper::toResponseDto);
	}

	@Transactional(readOnly = true)
	public Optional<ReportResponseDto> findByOrderId(Long orderId) {
		return reportRepository.findByOrder_Id(orderId).map(reportMapper::toResponseDto);
	}

	@Transactional(readOnly = true)
	public Page<ReportResponseDto> findAll(Pageable pageable) {
		return reportRepository.findAll(pageable).map(reportMapper::toResponseDto);
	}

	@Transactional(readOnly = true)
	public List<LineItemResponseDto> findLineItemsByReportId(Long reportId) {
		return lineItemRepository.findByReport_IdOrderByIdAsc(reportId).stream()
				.map(reportMapper::toResponseDto)
				.toList();
	}

	@Transactional
	public ReportResponseDto create(ReportRequestDto request) {
		Report report = reportMapper.toEntity(request);

		if (request.orderId() != null) {
			Order order = orderRepository.findById(request.orderId())
					.orElseThrow(() -> new OrderNotFoundException(request.orderId()));
			if (reportRepository.existsByOrder_Id(request.orderId())) {
				throw new OrderAlreadyHasReportException(request.orderId());
			}
			report.setOrder(order);
		}

		return reportMapper.toResponseDto(reportRepository.save(report));
	}

	@Transactional
	public ReportResponseDto update(Long id, ReportRequestDto request) {
		Report report = reportRepository.findById(id)
				.orElseThrow(() -> new ReportNotFoundException(id));

		reportMapper.updateEntity(request, report);
		report.setOrder(null);

		if (request.orderId() != null) {
			Order order = orderRepository.findById(request.orderId())
					.orElseThrow(() -> new OrderNotFoundException(request.orderId()));
			reportRepository.findByOrder_Id(request.orderId())
					.filter(existing -> !existing.getId().equals(id))
					.ifPresent(existing -> { throw new OrderAlreadyHasReportException(request.orderId()); });
			report.setOrder(order);
		}

		return reportMapper.toResponseDto(reportRepository.save(report));
	}

	@Transactional
	public void delete(Long id) {
		if (!reportRepository.existsById(id)) {
			throw new ReportNotFoundException(id);
		}
		lineItemRepository.deleteByReport_Id(id);
		reportRepository.deleteById(id);
	}

	@Transactional
	public LineItemResponseDto addLineItem(Long reportId, LineItemRequestDto request) {
		Report report = reportRepository.findById(reportId)
				.orElseThrow(() -> new ReportNotFoundException(reportId));
		LineItem lineItem = reportMapper.toEntity(request);
		lineItem.setReport(report);
		return reportMapper.toResponseDto(lineItemRepository.save(lineItem));
	}

	@Transactional
	public void deleteLineItem(Long reportId, Long lineItemId) {
		if (!reportRepository.existsById(reportId)) {
			throw new ReportNotFoundException(reportId);
		}
		LineItem lineItem = lineItemRepository.findById(lineItemId)
				.filter(item -> item.getReport().getId().equals(reportId))
				.orElseThrow(() -> new LineItemNotFoundException(lineItemId));
		lineItemRepository.delete(lineItem);
	}
}
