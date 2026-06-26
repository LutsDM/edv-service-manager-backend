package de.edvservice.manager.entity.report.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import de.edvservice.manager.config.GlobalMapperConfig;
import de.edvservice.manager.entity.lineItem.LineItem;
import de.edvservice.manager.entity.lineItem.LineItemRequestDto;
import de.edvservice.manager.entity.lineItem.LineItemResponseDto;
import de.edvservice.manager.entity.report.Report;
import de.edvservice.manager.entity.report.ReportRequestDto;
import de.edvservice.manager.entity.report.ReportResponseDto;

@Mapper(config = GlobalMapperConfig.class)
public interface ReportMapper {

	@Mapping(target = "orderId", source = "order.id")
	ReportResponseDto toResponseDto(Report report);

	@Mapping(target = "reportId", source = "report.id")
	LineItemResponseDto toResponseDto(LineItem lineItem);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "order", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "customerSignatureData", ignore = true)
	@Mapping(target = "employeeSignatureData", ignore = true)
	@Mapping(target = "customerSignedAt", ignore = true)
	@Mapping(target = "employeeSignedAt", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	Report toEntity(ReportRequestDto request);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "order", ignore = true)
	@Mapping(target = "status", ignore = true)
	@Mapping(target = "customerSignatureData", ignore = true)
	@Mapping(target = "employeeSignatureData", ignore = true)
	@Mapping(target = "customerSignedAt", ignore = true)
	@Mapping(target = "employeeSignedAt", ignore = true)
	@Mapping(target = "createdAt", ignore = true)
	@Mapping(target = "updatedAt", ignore = true)
	void updateEntity(ReportRequestDto request, @MappingTarget Report report);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "report", ignore = true)
	LineItem toEntity(LineItemRequestDto request);
}
