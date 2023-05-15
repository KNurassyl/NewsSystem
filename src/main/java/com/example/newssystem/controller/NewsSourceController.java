package com.example.newssystem.controller;

import com.example.newssystem.entity.NewsSource;
import com.example.newssystem.repository.NewsSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news_sources")
public class NewsSourceController {

    @Autowired
    private NewsSourceRepository newsSourceRepository;

    @GetMapping
    public List<NewsSource> getAllNewsSources() {
        return newsSourceRepository.findAll();
    }

    @GetMapping("/{id}")
    public NewsSource getNewsSourceById(@PathVariable Long id) {
        return newsSourceRepository.findById(id).orElse(null);
    }

    @PostMapping
    public NewsSource createNewsSource(@RequestBody NewsSource newsSource) {
        return newsSourceRepository.save(newsSource);
    }

    @PutMapping("/{id}")
    public NewsSource updateNewsSource(@PathVariable Long id, @RequestBody NewsSource newsSource) {
        NewsSource existingNewsSource = newsSourceRepository.findById(id).orElse(null);
        if (existingNewsSource != null) {
            existingNewsSource.setName(newsSource.getName());
            existingNewsSource.setUrl(newsSource.getUrl());
            return newsSourceRepository.save(existingNewsSource);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteNewsSource(@PathVariable Long id) {
        newsSourceRepository.deleteById(id);
    }

}
