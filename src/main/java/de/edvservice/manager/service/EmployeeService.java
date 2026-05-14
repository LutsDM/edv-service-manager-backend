package de.edvservice.manager.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.edvservice.manager.entity.employee.EmployeeDto;
import de.edvservice.manager.entity.employee.mapper.EmployeeMapper;
import de.edvservice.manager.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final EmployeeMapper employeeMapper;

	public Optional<EmployeeDto> findById(Long id) {
		return employeeRepository.findById(id).map(employeeMapper::toDto);
	}

	public Optional<EmployeeDto> findByEmail(String email) {
		return employeeRepository.findByEmailIgnoreCase(email).map(employeeMapper::toDto);
	}

	public Page<EmployeeDto> findAll(Pageable pageable) {
		return employeeRepository.findAll(pageable).map(employeeMapper::toDto);
	}
}
