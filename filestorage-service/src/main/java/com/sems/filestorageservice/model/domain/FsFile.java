package com.sems.filestorageservice.model.domain;


import com.sems.filestoragestarter.enums.FileDeleteType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "archive_package")
public class FsFile {

    @Id
    @SequenceGenerator(name = "fsFileIdSeq", sequenceName = "fs_file_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fsFileIdSeq")
    private Long id;

    @Column(name = "guid")
    private String guid;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private Long size;

    @Column(name = "path")
    private String path;

    @Column(name = "hash")
    private String hash;

    @CreatedDate
    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "deleted_by")
    private String deletedBy;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Column(name = "delete_Type")
    @Enumerated(value = EnumType.STRING)
    private FileDeleteType deleteType;

}
