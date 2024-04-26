package com.sems.newsservice.service;

import com.sems.newsservice.dto.NewsCreateDto;
import com.sems.newsservice.dto.NewsDto;
import com.sems.newsservice.dto.NewsUpdateDto;
import org.springframework.data.domain.Page;

public interface NewsService {

    Page<NewsDto> getPage(int limit, int offset);
    NewsDto save(NewsCreateDto newsDto);

    NewsDto updateById(Long id, NewsUpdateDto newsDto);



    void deleteById(Long id);
}
