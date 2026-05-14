package de.edvservice.manager.entity.report.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import de.edvservice.manager.config.GlobalMapperConfig;
import de.edvservice.manager.entity.lineItem.LineItem;
import de.edvservice.manager.entity.lineItem.LineItemDto;
import de.edvservice.manager.entity.report.ServiceReport;
import de.edvservice.manager.entity.report.ServiceReportDto;

@Mapper(config = GlobalMapperConfig.class)
public interface ServiceReportMapper {

	@Mapping(target = "serviceOrderId", source = "serviceOrder.id")
	ServiceReportDto toDto(ServiceReport serviceReport);

	@Mapping(target = "serviceReportId", source = "serviceReport.id")
	LineItemDto toDto(LineItem lineItem);
}
