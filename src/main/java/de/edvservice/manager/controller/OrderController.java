package de.edvservice.manager.controller;

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

import de.edvservice.manager.entity.order.OrderRequestDto;
import de.edvservice.manager.entity.order.OrderResponseDto;
import de.edvservice.manager.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping("/{id}")
	public ResponseEntity<OrderResponseDto> getById(@PathVariable Long id) {
		return orderService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(params = "orderNumber")
	public ResponseEntity<OrderResponseDto> getByOrderNumber(@RequestParam String orderNumber) {
		return orderService.findByOrderNumber(orderNumber)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public Page<OrderResponseDto> list(Pageable pageable) {
		return orderService.findAll(pageable);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderResponseDto create(@Valid @RequestBody OrderRequestDto request) {
		return orderService.create(request);
	}

	@PutMapping("/{id}")
	public OrderResponseDto update(@PathVariable Long id, @Valid @RequestBody OrderRequestDto request) {
		return orderService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		orderService.delete(id);
	}
}
