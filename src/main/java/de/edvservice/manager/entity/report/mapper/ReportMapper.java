package de.edvservice.manager.entity.report.mapper;

import org.mapstruct.Mapper;

import de.edvservice.manager.config.GlobalMapperConfig;
import de.edvservice.manager.entity.lineItem.LineItem;
import de.edvservice.manager.entity.lineItem.LineItemResponseDto;
import de.edvservice.manager.entity.report.Report;
import de.edvservice.manager.entity.report.ReportResponseDto;

@Mapper(config = GlobalMapperConfig.class)
public interface ReportMapper {

	ReportResponseDto toResponseDto(Report report);

	LineItemResponseDto toResponseDto(LineItem lineItem);
}
