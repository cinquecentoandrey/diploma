package com.sems.filestoragestarter.dto;

import com.sems.filestoragestarter.enums.FileDeleteType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FsFileDto {

    private Long id;
    private String guid;
    private String originalName;
    private String type;
    private Long size;
    private String path;
    private String hash;
    private LocalDateTime created;
    private Boolean isDeleted;
    private String deletedBy;
    private LocalDateTime deletedDate;
    private FileDeleteType deleteType;

}
