package de.edvservice.manager.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
	componentModel = MappingConstants.ComponentModel.SPRING,
	injectionStrategy = InjectionStrategy.CONSTRUCTOR,
	unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface GlobalMapperConfig {
}
