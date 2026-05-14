package de.edvservice.manager.entity.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import de.edvservice.manager.config.GlobalMapperConfig;
import de.edvservice.manager.entity.order.ServiceOrder;
import de.edvservice.manager.entity.order.ServiceOrderDto;


@Mapper(config = GlobalMapperConfig.class)
public interface ServiceOrderMapper {

	@Mapping(target = "customerId", source = "customer.id")
	@Mapping(target = "employeeId", source = "employee.id")
	ServiceOrderDto toDto(ServiceOrder serviceOrder);
}
