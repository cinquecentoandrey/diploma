package com.sems.newsservice.controller;

import com.sems.newsservice.dto.NewsCreateDto;
import com.sems.newsservice.dto.NewsDto;
import com.sems.newsservice.dto.NewsUpdateDto;
import com.sems.newsservice.dto.TagDto;
import com.sems.newsservice.enums.NewsTag;
import com.sems.newsservice.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    // todo filters sort
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<NewsDto>> getPage(@RequestParam(name = "limit") Integer limit,
                                                 @RequestParam(name = "offset") Integer offset) {
        return ResponseEntity.ok(newsService.getPage(limit, offset));
    }

    @PostMapping(
            value = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<NewsDto> updateById(@PathVariable("id") Long id, @RequestBody NewsUpdateDto updateDto) {
        return ResponseEntity.ok(newsService.updateById(id, updateDto));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<NewsDto> save(@RequestBody NewsCreateDto newsCreateDto) {
        return ResponseEntity.ok(newsService.save(newsCreateDto));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        newsService.deleteById(id);
    }

    @GetMapping("/tags")
    public ResponseEntity<TagDto> tags() {
        return ResponseEntity.ok(
                TagDto.builder()
                        .tags(List.of(NewsTag.values()))
                        .build()
        );
    }
}
