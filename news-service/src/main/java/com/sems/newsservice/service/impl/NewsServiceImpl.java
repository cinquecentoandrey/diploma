package com.sems.newsservice.service.impl;

import com.sems.common.enums.ErrorMessage;
import com.sems.common.exception.EntityNotFoundException;
import com.sems.newsservice.dto.NewsCreateDto;
import com.sems.newsservice.dto.NewsDto;
import com.sems.newsservice.dto.NewsUpdateDto;
import com.sems.newsservice.exception.DuplicatedNewsNameException;
import com.sems.newsservice.mapper.NewsMapper;
import com.sems.newsservice.model.News;
import com.sems.newsservice.repository.NewsRepository;
import com.sems.newsservice.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;


    // todo custom sort + specification\
    @Override
    @Transactional(readOnly = true)
    public Page<NewsDto> getPage(int limit, int offset) {
        return new PageImpl<>(
                newsRepository.findAll(PageRequest.of(offset, limit))
                        .stream()
                        .map(newsMapper::domainToDto)
                        .toList()
        );
    }

    @Override
    @Transactional
    public NewsDto save(NewsCreateDto newsDto) {
        existsByHeader(newsDto.getHeader());

        News news = newsMapper.createToDomain(newsDto);
       // news.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
       // news.setUpdateBy(SecurityContextHolder.getContext().getAuthentication().getName());

        return newsMapper.domainToDto(newsRepository.save(news));
    }


    @Override
    @Transactional
    public NewsDto updateById(Long id, NewsUpdateDto newsDto) {
        existsByHeader(newsDto.getHeader());

        News newsToUpdate = getById(id);
        newsToUpdate.setBody(newsDto.getBody());
        newsToUpdate.setHeader(newsDto.getHeader());
        newsToUpdate.setTag(newsDto.getTag());

        return newsMapper.domainToDto(newsRepository.save(newsToUpdate));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    private News getById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND.getMessage()));
    }

    private void existsByHeader(String header) {
        newsRepository.findNewsByHeader(header)
                .ifPresent(news -> {throw new DuplicatedNewsNameException(ErrorMessage.NEWS_ALREADY_EXSITS.getMessage());});
    }
}
