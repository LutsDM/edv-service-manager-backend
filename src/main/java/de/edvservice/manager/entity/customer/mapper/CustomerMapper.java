package de.edvservice.manager.entity.customer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import de.edvservice.manager.config.GlobalMapperConfig;
import de.edvservice.manager.entity.customer.Customer;
import de.edvservice.manager.entity.customer.CustomerRequestDto;
import de.edvservice.manager.entity.customer.CustomerResponseDto;

@Mapper(config = GlobalMapperConfig.class)
public interface CustomerMapper {

	CustomerResponseDto toResponseDto(Customer customer);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	Customer toEntity(CustomerRequestDto request);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	void updateEntity(CustomerRequestDto request, @MappingTarget Customer customer);
}
