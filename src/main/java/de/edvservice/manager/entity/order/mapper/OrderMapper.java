package de.edvservice.manager.entity.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import de.edvservice.manager.config.GlobalMapperConfig;
import de.edvservice.manager.entity.order.Order;
import de.edvservice.manager.entity.order.OrderRequestDto;
import de.edvservice.manager.entity.order.OrderResponseDto;

@Mapper(config = GlobalMapperConfig.class)
public interface OrderMapper {

	@Mapping(target = "customerId", source = "customer.id")
	@Mapping(target = "employeeId", source = "employee.id")
	OrderResponseDto toResponseDto(Order order);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "customer", ignore = true)
	@Mapping(target = "employee", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	Order toEntity(OrderRequestDto request);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "customer", ignore = true)
	@Mapping(target = "employee", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	void updateEntity(OrderRequestDto request, @MappingTarget Order order);
}
