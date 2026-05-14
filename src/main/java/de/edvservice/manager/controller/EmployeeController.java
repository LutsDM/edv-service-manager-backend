package de.edvservice.manager.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.edvservice.manager.entity.employee.EmployeeDto;
import de.edvservice.manager.service.EmployeeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDto> getById(@PathVariable Long id) {
		return employeeService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(params = "email")
	public ResponseEntity<EmployeeDto> getByEmail(@RequestParam String email) {
		return employeeService.findByEmail(email)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public Page<EmployeeDto> list(Pageable pageable) {
		return employeeService.findAll(pageable);
	}
}
