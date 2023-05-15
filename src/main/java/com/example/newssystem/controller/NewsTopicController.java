package com.example.newssystem.controller;

import com.example.newssystem.entity.NewsTopic;
import com.example.newssystem.repository.NewsTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news_topics")
public class NewsTopicController {
    @Autowired
    private NewsTopicRepository newsTopicRepository;

    @GetMapping
    public List<NewsTopic> getAllNewsTopics() {
        return newsTopicRepository.findAll();
    }

    @GetMapping("/{id}")
    public NewsTopic getNewsTopicById(@PathVariable Long id) {
        return newsTopicRepository.findById(id).orElse(null);
    }

    @PostMapping
    public NewsTopic createNewsTopic(@RequestBody NewsTopic newsTopic) {
        return newsTopicRepository.save(newsTopic);
    }

    @PutMapping("/{id}")
    public NewsTopic updateNewsTopic(@PathVariable Long id, @RequestBody NewsTopic newsTopic) {
        NewsTopic existingNewsTopic = newsTopicRepository.findById(id).orElse(null);
        if (existingNewsTopic != null) {
            existingNewsTopic.setName(newsTopic.getName());
            return newsTopicRepository.save(existingNewsTopic);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteNewsTopic(@PathVariable Long id) {
        newsTopicRepository.deleteById(id);
    }

}
