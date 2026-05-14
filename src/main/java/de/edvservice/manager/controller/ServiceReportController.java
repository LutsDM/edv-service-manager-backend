package de.edvservice.manager.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.edvservice.manager.entity.lineItem.LineItemDto;
import de.edvservice.manager.entity.report.ServiceReportDto;
import de.edvservice.manager.service.ServiceReportService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/service-reports")
@RequiredArgsConstructor
public class ServiceReportController {

	private final ServiceReportService serviceReportService;

	@GetMapping("/{id}")
	public ResponseEntity<ServiceReportDto> getById(@PathVariable Long id) {
		return serviceReportService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(params = "serviceOrderId")
	public ResponseEntity<ServiceReportDto> getByServiceOrderId(@RequestParam Long serviceOrderId) {
		return serviceReportService.findByServiceOrderId(serviceOrderId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}/line-items")
	public List<LineItemDto> listLineItems(@PathVariable Long id) {
		return serviceReportService.findLineItemsByReportId(id);
	}

	@GetMapping
	public Page<ServiceReportDto> list(Pageable pageable) {
		return serviceReportService.findAll(pageable);
	}
}
