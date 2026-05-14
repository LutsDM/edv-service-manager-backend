package de.edvservice.manager.entity.customer.mapper;

import org.mapstruct.Mapper;

import de.edvservice.manager.config.GlobalMapperConfig;
import de.edvservice.manager.entity.customer.Customer;
import de.edvservice.manager.entity.customer.CustomerDto;

@Mapper(config = GlobalMapperConfig.class)
public interface CustomerMapper {

	CustomerDto toDto(Customer customer);
}
