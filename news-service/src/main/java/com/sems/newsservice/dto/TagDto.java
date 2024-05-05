package com.sems.newsservice.dto;

import com.sems.newsservice.enums.NewsTag;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagDto {

    private List<NewsTag> tags;
}
