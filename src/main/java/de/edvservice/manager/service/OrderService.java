package de.edvservice.manager.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.edvservice.manager.entity.customer.Customer;
import de.edvservice.manager.entity.employee.Employee;
import de.edvservice.manager.entity.order.Order;
import de.edvservice.manager.entity.order.OrderRequestDto;
import de.edvservice.manager.entity.order.OrderResponseDto;
import de.edvservice.manager.entity.order.mapper.OrderMapper;
import de.edvservice.manager.exception.CustomerNotFoundException;
import de.edvservice.manager.exception.DuplicateOrderNumberException;
import de.edvservice.manager.exception.EmployeeNotFoundException;
import de.edvservice.manager.exception.OrderAlreadyHasReportException;
import de.edvservice.manager.exception.OrderNotFoundException;
import de.edvservice.manager.repository.CustomerRepository;
import de.edvservice.manager.repository.EmployeeRepository;
import de.edvservice.manager.repository.OrderRepository;
import de.edvservice.manager.repository.ReportRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final CustomerRepository customerRepository;
	private final EmployeeRepository employeeRepository;
	private final ReportRepository reportRepository;
	private final OrderMapper orderMapper;

	@Transactional(readOnly = true)
	public Optional<OrderResponseDto> findById(Long id) {
		return orderRepository.findById(id).map(orderMapper::toResponseDto);
	}

	@Transactional(readOnly = true)
	public Optional<OrderResponseDto> findByOrderNumber(String orderNumber) {
		return orderRepository.findByOrderNumber(orderNumber).map(orderMapper::toResponseDto);
	}

	@Transactional(readOnly = true)
	public Page<OrderResponseDto> findAll(Pageable pageable) {
		return orderRepository.findAll(pageable).map(orderMapper::toResponseDto);
	}

	@Transactional
	public OrderResponseDto create(OrderRequestDto request) {
		if (orderRepository.existsByOrderNumber(request.orderNumber())) {
			throw new DuplicateOrderNumberException(request.orderNumber());
		}
		Customer customer = customerRepository.findById(request.customerId())
				.orElseThrow(() -> new CustomerNotFoundException(request.customerId()));

		Order order = orderMapper.toEntity(request);
		order.setCustomer(customer);
		order.setEmployeeCount(request.employeeCount() != null ? request.employeeCount() : 1);

		if (request.employeeId() != null) {
			Employee employee = employeeRepository.findById(request.employeeId())
					.orElseThrow(() -> new EmployeeNotFoundException(request.employeeId()));
			order.setEmployee(employee);
		}

		applyCustomerSnapshot(order, customer);

		return orderMapper.toResponseDto(orderRepository.save(order));
	}

	@Transactional
	public OrderResponseDto update(Long id, OrderRequestDto request) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException(id));

		if (orderRepository.existsByOrderNumberAndIdNot(request.orderNumber(), id)) {
			throw new DuplicateOrderNumberException(request.orderNumber());
		}

		Customer customer = customerRepository.findById(request.customerId())
				.orElseThrow(() -> new CustomerNotFoundException(request.customerId()));

		orderMapper.updateEntity(request, order);
		order.setCustomer(customer);
		order.setEmployeeCount(request.employeeCount() != null ? request.employeeCount() : order.getEmployeeCount());
		order.setEmployee(null);

		if (request.employeeId() != null) {
			Employee employee = employeeRepository.findById(request.employeeId())
					.orElseThrow(() -> new EmployeeNotFoundException(request.employeeId()));
			order.setEmployee(employee);
		}

		applyCustomerSnapshot(order, customer);

		return orderMapper.toResponseDto(orderRepository.save(order));
	}

	@Transactional
	public void delete(Long id) {
		if (!orderRepository.existsById(id)) {
			throw new OrderNotFoundException(id);
		}
		if (reportRepository.existsByOrder_Id(id)) {
			throw new OrderAlreadyHasReportException(id);
		}
		orderRepository.deleteById(id);
	}

	private void applyCustomerSnapshot(Order order, Customer customer) {
		order.setCustomerNumberSnapshot(customer.getCustomerNumber());
		order.setCustomerTypeSnapshot(customer.getType() != null ? customer.getType().name() : null);
		order.setCompanyNameSnapshot(customer.getCompanyName());
		order.setFirstNameSnapshot(customer.getFirstName());
		order.setLastNameSnapshot(customer.getLastName());
		order.setPhoneSnapshot(customer.getPhone());
		order.setMobileSnapshot(customer.getMobile());
		order.setEmailSnapshot(customer.getEmail());
		order.setStreetSnapshot(customer.getStreet());
		order.setHouseNumberSnapshot(customer.getHouseNumber());
		order.setZipCodeSnapshot(customer.getZipCode());
		order.setCitySnapshot(customer.getCity());
	}
}
