package de.edvservice.manager.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.edvservice.manager.entity.customer.Customer;
import de.edvservice.manager.entity.customer.CustomerRequestDto;
import de.edvservice.manager.entity.customer.CustomerResponseDto;
import de.edvservice.manager.entity.customer.mapper.CustomerMapper;
import de.edvservice.manager.exception.CustomerInUseException;
import de.edvservice.manager.exception.CustomerNotFoundException;
import de.edvservice.manager.exception.DuplicateCustomerNumberException;
import de.edvservice.manager.repository.CustomerRepository;
import de.edvservice.manager.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final OrderRepository orderRepository;
	private final CustomerMapper customerMapper;

	@Transactional(readOnly = true)
	public Optional<CustomerResponseDto> findById(Long id) {
		return customerRepository.findById(id).map(customerMapper::toResponseDto);
	}

	@Transactional(readOnly = true)
	public Optional<CustomerResponseDto> findByCustomerNumber(String customerNumber) {
		return customerRepository.findByCustomerNumber(customerNumber).map(customerMapper::toResponseDto);
	}

	@Transactional(readOnly = true)
	public Page<CustomerResponseDto> findAll(Pageable pageable) {
		return customerRepository.findAll(pageable).map(customerMapper::toResponseDto);
	}

	@Transactional
	public CustomerResponseDto create(CustomerRequestDto request) {
		if (customerRepository.existsByCustomerNumber(request.customerNumber())) {
			throw new DuplicateCustomerNumberException(request.customerNumber());
		}
		Customer customer = customerMapper.toEntity(request);
		return customerMapper.toResponseDto(customerRepository.save(customer));
	}

	@Transactional
	public CustomerResponseDto update(Long id, CustomerRequestDto request) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException(id));
		if (customerRepository.existsByCustomerNumberAndIdNot(request.customerNumber(), id)) {
			throw new DuplicateCustomerNumberException(request.customerNumber());
		}
		customerMapper.updateEntity(request, customer);
		return customerMapper.toResponseDto(customerRepository.save(customer));
	}

	@Transactional
	public void delete(Long id) {
		if (!customerRepository.existsById(id)) {
			throw new CustomerNotFoundException(id);
		}
		if (orderRepository.existsByCustomer_Id(id)) {
			throw new CustomerInUseException(id);
		}
		customerRepository.deleteById(id);
	}
}
