package com.sternitc.boundary.mongodb.application.domain.service;

import com.sternitc.boundary.mongodb.adapter.out.persistence.BoundaryDocument;
import com.sternitc.boundary.mongodb.application.port.BoundaryDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")
public interface BoundaryMapper {

    @Mapping(source = "dto.id", target = "id")
    @Mapping(source = "dto.companyId.id", target = "companyId")
    @Mapping(source = "dto.userId.id", target = "userId")
    @Mapping(source = "dto.articleId.id", target = "articleId")
    @Mapping(source = "dto.boundaryType.type", target = "boundaryType")
    @Mapping(source = "dto.boundaryValue.value", target = "boundaryValue")
    BoundaryDocument toDocument(BoundaryDao.BoundaryDto dto);

    @Mapping(source = "document.id", target = "id")
    @Mapping(source = "document.companyId", target = "companyId.id")
    @Mapping(source = "document.userId", target = "userId.id")
    @Mapping(source = "document.articleId", target = "articleId.id")
    @Mapping(source = "document.boundaryType", target = "boundaryType.type")
    @Mapping(source = "document.boundaryValue", target = "boundaryValue.value")
    BoundaryDao.BoundaryDto toDto(BoundaryDocument document);

}
