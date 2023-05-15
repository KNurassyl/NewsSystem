package com.example.newssystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Date publishedAt;

    @ManyToOne
    @JoinColumn(name = "sourceId")
    private NewsSource source;

    @ManyToOne
    @JoinColumn(name = "topicId")
    private NewsTopic topic;

    // getters and setters
}
