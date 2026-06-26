package de.edvservice.manager.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.edvservice.manager.entity.lineItem.LineItemRequestDto;
import de.edvservice.manager.entity.lineItem.LineItemResponseDto;
import de.edvservice.manager.entity.report.ReportRequestDto;
import de.edvservice.manager.entity.report.ReportResponseDto;
import de.edvservice.manager.service.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

	private final ReportService reportService;

	@GetMapping("/{id}")
	public ResponseEntity<ReportResponseDto> getById(@PathVariable Long id) {
		return reportService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(params = "orderId")
	public ResponseEntity<ReportResponseDto> getByOrderId(@RequestParam Long orderId) {
		return reportService.findByOrderId(orderId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/{id}/line-items")
	public List<LineItemResponseDto> listLineItems(@PathVariable Long id) {
		return reportService.findLineItemsByReportId(id);
	}

	@GetMapping
	public Page<ReportResponseDto> list(Pageable pageable) {
		return reportService.findAll(pageable);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ReportResponseDto create(@Valid @RequestBody ReportRequestDto request) {
		return reportService.create(request);
	}

	@PutMapping("/{id}")
	public ReportResponseDto update(@PathVariable Long id, @Valid @RequestBody ReportRequestDto request) {
		return reportService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		reportService.delete(id);
	}

	@PostMapping("/{id}/line-items")
	@ResponseStatus(HttpStatus.CREATED)
	public LineItemResponseDto addLineItem(@PathVariable Long id, @Valid @RequestBody LineItemRequestDto request) {
		return reportService.addLineItem(id, request);
	}

	@DeleteMapping("/{id}/line-items/{lineItemId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLineItem(@PathVariable Long id, @PathVariable Long lineItemId) {
		reportService.deleteLineItem(id, lineItemId);
	}
}
