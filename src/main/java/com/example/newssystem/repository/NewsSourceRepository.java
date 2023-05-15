package com.example.newssystem.repository;

import com.example.newssystem.entity.NewsSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsSourceRepository extends JpaRepository<NewsSource, Long> {
}
