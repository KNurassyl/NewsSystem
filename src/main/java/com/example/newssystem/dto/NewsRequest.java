package com.example.newssystem.dto;

import com.example.newssystem.entity.NewsSource;
import com.example.newssystem.entity.NewsTopic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsRequest {
    private String title;
    private String content;
    private Date publishedAt;
    private NewsSource source;
    private NewsTopic topic;
}
