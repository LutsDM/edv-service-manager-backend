package de.edvservice.manager.entity.order.mapper;

import org.mapstruct.Mapper;

import de.edvservice.manager.config.GlobalMapperConfig;
import de.edvservice.manager.entity.order.Order;
import de.edvservice.manager.entity.order.OrderResponseDto;

@Mapper(config = GlobalMapperConfig.class)
public interface OrderMapper {

	OrderResponseDto toResponseDto(Order order);
}
