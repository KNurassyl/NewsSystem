package com.example.newssystem.repository;

import com.example.newssystem.entity.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findBySourceId(Long sourceId, Pageable pageable);
    List<News> findByTopicId(Long topicId, Pageable pageable);

}
