package com.sems.newsservice.dto;

import com.sems.newsservice.enums.NewsTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsDto {

    private Long id;
    private String header;
    private String body;
    private LocalDateTime created;
    private String createdBy;
    private LocalDateTime updated;
    private String updateBy;
    private NewsTag tag;
}
