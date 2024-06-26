package com.sems.newsservice.repository;

import com.sems.newsservice.enums.NewsTag;
import com.sems.newsservice.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

    Optional<News> findNewsByHeader(String header);

    List<News> findAllByTag(NewsTag tag);

}
