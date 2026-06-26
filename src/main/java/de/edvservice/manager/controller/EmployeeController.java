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

import de.edvservice.manager.entity.employee.EmployeeRequestDto;
import de.edvservice.manager.entity.employee.EmployeeResponseDto;
import de.edvservice.manager.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDto> getById(@PathVariable Long id) {
		return employeeService.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping(params = "email")
	public ResponseEntity<EmployeeResponseDto> getByEmail(@RequestParam String email) {
		return employeeService.findByEmail(email)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping
	public Page<EmployeeResponseDto> list(Pageable pageable) {
		return employeeService.findAll(pageable);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmployeeResponseDto create(@Valid @RequestBody EmployeeRequestDto request) {
		return employeeService.create(request);
	}

	@PutMapping("/{id}")
	public EmployeeResponseDto update(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDto request) {
		return employeeService.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		employeeService.delete(id);
	}
}
