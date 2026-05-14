package de.edvservice.manager.entity.employee.mapper;

import org.mapstruct.Mapper;

import de.edvservice.manager.config.GlobalMapperConfig;
import de.edvservice.manager.entity.employee.Employee;
import de.edvservice.manager.entity.employee.EmployeeDto;

@Mapper(config = GlobalMapperConfig.class)
public interface EmployeeMapper {

	EmployeeDto toDto(Employee employee);
}
