package de.edvservice.manager.entity.employee.mapper;

import org.mapstruct.Mapper;

import de.edvservice.manager.config.GlobalMapperConfig;
import de.edvservice.manager.entity.employee.Employee;
import de.edvservice.manager.entity.employee.EmployeeResponseDto;

@Mapper(config = GlobalMapperConfig.class)
public interface EmployeeMapper {

	EmployeeResponseDto toResponseDto(Employee employee);
}
