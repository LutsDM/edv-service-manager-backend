package de.edvservice.manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.edvservice.manager.entity.lineItem.LineItemDto;
import de.edvservice.manager.entity.report.ServiceReportDto;
import de.edvservice.manager.entity.report.mapper.ServiceReportMapper;
import de.edvservice.manager.repository.LineItemRepository;
import de.edvservice.manager.repository.ServiceReportRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ServiceReportService {

	private final ServiceReportRepository serviceReportRepository;
	private final LineItemRepository lineItemRepository;
	private final ServiceReportMapper serviceReportMapper;

	public Optional<ServiceReportDto> findById(Long id) {
		return serviceReportRepository.findById(id).map(serviceReportMapper::toDto);
	}

	public Optional<ServiceReportDto> findByServiceOrderId(Long serviceOrderId) {
		return serviceReportRepository.findByServiceOrder_Id(serviceOrderId).map(serviceReportMapper::toDto);
	}

	public Page<ServiceReportDto> findAll(Pageable pageable) {
		return serviceReportRepository.findAll(pageable).map(serviceReportMapper::toDto);
	}

	public List<LineItemDto> findLineItemsByReportId(Long serviceReportId) {
		return lineItemRepository.findByServiceReport_IdOrderByIdAsc(serviceReportId).stream()
				.map(serviceReportMapper::toDto)
				.toList();
	}
}
