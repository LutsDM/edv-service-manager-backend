package de.edvservice.manager.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.edvservice.manager.entity.employee.Employee;
import de.edvservice.manager.entity.employee.EmployeeRequestDto;
import de.edvservice.manager.entity.employee.EmployeeResponseDto;
import de.edvservice.manager.entity.employee.mapper.EmployeeMapper;
import de.edvservice.manager.exception.DuplicateEmployeeEmailException;
import de.edvservice.manager.exception.EmployeeInUseException;
import de.edvservice.manager.exception.EmployeeNotFoundException;
import de.edvservice.manager.repository.EmployeeRepository;
import de.edvservice.manager.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final OrderRepository orderRepository;
	private final EmployeeMapper employeeMapper;

	@Transactional(readOnly = true)
	public Optional<EmployeeResponseDto> findById(Long id) {
		return employeeRepository.findById(id).map(employeeMapper::toResponseDto);
	}

	@Transactional(readOnly = true)
	public Optional<EmployeeResponseDto> findByEmail(String email) {
		return employeeRepository.findByEmailIgnoreCase(email).map(employeeMapper::toResponseDto);
	}

	@Transactional(readOnly = true)
	public Page<EmployeeResponseDto> findAll(Pageable pageable) {
		return employeeRepository.findAll(pageable).map(employeeMapper::toResponseDto);
	}

	@Transactional
	public EmployeeResponseDto create(EmployeeRequestDto request) {
		if (employeeRepository.existsByEmailIgnoreCase(request.email())) {
			throw new DuplicateEmployeeEmailException(request.email());
		}
		Employee employee = employeeMapper.toEntity(request);
		return employeeMapper.toResponseDto(employeeRepository.save(employee));
	}

	@Transactional
	public EmployeeResponseDto update(Long id, EmployeeRequestDto request) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
		if (employeeRepository.existsByEmailIgnoreCaseAndIdNot(request.email(), id)) {
			throw new DuplicateEmployeeEmailException(request.email());
		}
		employeeMapper.updateEntity(request, employee);
		return employeeMapper.toResponseDto(employeeRepository.save(employee));
	}

	@Transactional
	public void delete(Long id) {
		if (!employeeRepository.existsById(id)) {
			throw new EmployeeNotFoundException(id);
		}
		if (orderRepository.existsByEmployee_Id(id)) {
			throw new EmployeeInUseException(id);
		}
		employeeRepository.deleteById(id);
	}
}
