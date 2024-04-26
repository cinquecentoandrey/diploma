package com.sems.newsservice.dto;

import com.sems.newsservice.enums.NewsTag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsUpdateDto {

    private String header;
    private String body;
    private NewsTag tag;

}
