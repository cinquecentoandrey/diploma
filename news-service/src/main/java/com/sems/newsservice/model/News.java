package com.sems.newsservice.model;

import com.sems.newsservice.enums.NewsTag;
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
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "news")
public class News {

    @Id
    @SequenceGenerator(name = "newsIdSeq", sequenceName = "news_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "newsIdSeq")
    private Long id;

    @Column(name = "header")
    private String header;

    @Column(name = "body")
    private String body;

    @CreatedDate
    @Column(name = "created")
    private LocalDateTime created;

    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated")
    private LocalDateTime updated;

    @Column(name = "updated_by")
    private String updateBy;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "tag")
    private NewsTag tag;

}
