package com.sems.newsservice.mapper;

import com.sems.newsservice.dto.NewsCreateDto;
import com.sems.newsservice.dto.NewsDto;
import com.sems.newsservice.model.News;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsDto domainToDto(News domain);

    News dtoToDomain(NewsDto dto);

    News createToDomain(NewsCreateDto create);

}
