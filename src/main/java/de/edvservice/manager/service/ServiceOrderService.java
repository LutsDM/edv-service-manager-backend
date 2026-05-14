package de.edvservice.manager.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.edvservice.manager.entity.order.ServiceOrderDto;
import de.edvservice.manager.entity.order.mapper.ServiceOrderMapper;
import de.edvservice.manager.repository.ServiceOrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ServiceOrderService {

	private final ServiceOrderRepository serviceOrderRepository;
	private final ServiceOrderMapper serviceOrderMapper;

	public Optional<ServiceOrderDto> findById(Long id) {
		return serviceOrderRepository.findById(id).map(serviceOrderMapper::toDto);
	}

	public Optional<ServiceOrderDto> findByOrderNumber(String orderNumber) {
		return serviceOrderRepository.findByOrderNumber(orderNumber).map(serviceOrderMapper::toDto);
	}

	public Page<ServiceOrderDto> findAll(Pageable pageable) {
		return serviceOrderRepository.findAll(pageable).map(serviceOrderMapper::toDto);
	}
}
