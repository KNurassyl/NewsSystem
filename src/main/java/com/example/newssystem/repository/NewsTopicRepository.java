package com.example.newssystem.repository;

import com.example.newssystem.entity.NewsSource;
import com.example.newssystem.entity.NewsTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsTopicRepository extends JpaRepository<NewsTopic, Long> {
}
