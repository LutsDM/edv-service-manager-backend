package de.edvservice.manager.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.edvservice.manager.entity.customer.CustomerDto;
import de.edvservice.manager.entity.customer.mapper.CustomerMapper;
import de.edvservice.manager.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;

	public Optional<CustomerDto> findById(Long id) {
		return customerRepository.findById(id).map(customerMapper::toDto);
	}

	public Optional<CustomerDto> findByCustomerNumber(String customerNumber) {
		return customerRepository.findByCustomerNumber(customerNumber).map(customerMapper::toDto);
	}

	public Page<CustomerDto> findAll(Pageable pageable) {
		return customerRepository.findAll(pageable).map(customerMapper::toDto);
	}
}
