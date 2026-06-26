package de.edvservice.manager.entity.employee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import de.edvservice.manager.config.GlobalMapperConfig;
import de.edvservice.manager.entity.employee.Employee;
import de.edvservice.manager.entity.employee.EmployeeRequestDto;
import de.edvservice.manager.entity.employee.EmployeeResponseDto;

@Mapper(config = GlobalMapperConfig.class)
public interface EmployeeMapper {

	EmployeeResponseDto toResponseDto(Employee employee);

	@Mapping(target = "id", ignore = true)
	Employee toEntity(EmployeeRequestDto request);

	@Mapping(target = "id", ignore = true)
	void updateEntity(EmployeeRequestDto request, @MappingTarget Employee employee);
}
