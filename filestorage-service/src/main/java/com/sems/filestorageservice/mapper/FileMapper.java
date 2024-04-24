package com.sems.filestorageservice.mapper;

import com.sems.filestorageservice.model.domain.FsFile;
import com.sems.filestorageservice.model.dto.FsFileDto;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface FileMapper {

    FsFileDto domainToDto(FsFile domain);

    FsFile dtoToDomain(FsFileDto dto);

}
