package de.edvservice.manager.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.edvservice.manager.entity.order.OrderResponseDto;
import de.edvservice.manager.entity.order.mapper.OrderMapper;
import de.edvservice.manager.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;

	public Optional<OrderResponseDto> findById(Long id) {
		return orderRepository.findById(id).map(orderMapper::toResponseDto);
	}

	public Optional<OrderResponseDto> findByOrderNumber(String orderNumber) {
		return orderRepository.findByOrderNumber(orderNumber).map(orderMapper::toResponseDto);
	}

	public Page<OrderResponseDto> findAll(Pageable pageable) {
		return orderRepository.findAll(pageable).map(orderMapper::toResponseDto);
	}
}
