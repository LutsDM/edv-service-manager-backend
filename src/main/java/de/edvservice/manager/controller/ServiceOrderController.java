package de.edvservice.manager.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.edvservice.manager.entity.order.ServiceOrderDto;
import de.edvservice.manager.service.ServiceOrderService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/service-orders")
@RequiredArgsConstructor
public class ServiceOrderController {

	private final ServiceOrderService serviceOrderService;

	@GetMapping("/{id}")
	public ResponseEntity<ServiceOrderDto> getById(@PathVariable Long id) {
		return serviceOrderService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(params = "orderNumber")
	public ResponseEntity<ServiceOrderDto> getByOrderNumber(@RequestParam String orderNumber) {
		return serviceOrderService.findByOrderNumber(orderNumber)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public Page<ServiceOrderDto> list(Pageable pageable) {
		return serviceOrderService.findAll(pageable);
	}
}
