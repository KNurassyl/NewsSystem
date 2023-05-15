package com.example.newssystem.controller;

import com.example.newssystem.dto.NewsRequest;
import com.example.newssystem.entity.News;
import com.example.newssystem.entity.NewsSource;
import com.example.newssystem.entity.NewsTopic;
import com.example.newssystem.repository.NewsRepository;
import com.example.newssystem.repository.NewsSourceRepository;
import com.example.newssystem.repository.NewsTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsSourceRepository sourceRepository;

    @Autowired
    private NewsTopicRepository topicRepository;

    @GetMapping
    public List<News> getAllNews(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<News> newsPage = newsRepository.findAll(pageable);
        return newsPage.getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<News> getNewsById(@PathVariable Long id) {
        Optional<News> news = newsRepository.findById(id);
        return news.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/by_source/{sourceId}")
    public List<News> getNewsBySourceId(@PathVariable Long sourceId, Pageable pageable) {
        return newsRepository.findBySourceId(sourceId, pageable);
    }

    @GetMapping("/by_topic/{topicId}")
    public List<News> getNewsByTopicId(@PathVariable Long topicId, Pageable pageable) {
        return newsRepository.findByTopicId(topicId, pageable);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
            super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        }
    }

    @PostMapping
    public News createNews(@RequestBody NewsRequest newsRequest) {
        News news = new News();
        news.setTitle(newsRequest.getTitle());
        news.setContent(newsRequest.getContent());
        news.setPublishedAt(newsRequest.getPublishedAt());

        // Get the source and topic entities by their ID values
        NewsSource source = sourceRepository.findById(newsRequest.getSource().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Source", "id", newsRequest.getSource().getId()));
        NewsTopic topic = topicRepository.findById(newsRequest.getTopic().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic", "id", newsRequest.getTopic().getId()));
        news.setSource(source);
        news.setTopic(topic);

        return newsRepository.save(news);
    }


    @PutMapping("/{id}")
    public ResponseEntity<News> updateNews(@PathVariable Long id, @RequestBody News newsDetails) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isPresent()) {
            News updatedNews = news.get();
            NewsSource source = sourceRepository.findById(newsDetails.getSource().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Source", "id", newsDetails.getSource().getId()));
            NewsTopic topic = topicRepository.findById(newsDetails.getTopic().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Topic", "id", newsDetails.getTopic().getId()));
            updatedNews.setTitle(newsDetails.getTitle());
            updatedNews.setContent(newsDetails.getContent());
            updatedNews.setPublishedAt(newsDetails.getPublishedAt());
            updatedNews.setSource(source);
            updatedNews.setTopic(topic);
            return ResponseEntity.ok(newsRepository.save(updatedNews));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        Optional<News> news = newsRepository.findById(id);
        if (news.isPresent()) {
            newsRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
