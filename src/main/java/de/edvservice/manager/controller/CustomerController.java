package de.edvservice.manager.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.edvservice.manager.entity.customer.CustomerDto;
import de.edvservice.manager.service.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getById(@PathVariable Long id) {
		return customerService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(params = "customerNumber")
	public ResponseEntity<CustomerDto> getByCustomerNumber(@RequestParam String customerNumber) {
		return customerService.findByCustomerNumber(customerNumber)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public Page<CustomerDto> list(Pageable pageable) {
		return customerService.findAll(pageable);
	}
}
