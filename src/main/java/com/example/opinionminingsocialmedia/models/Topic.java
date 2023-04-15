package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private Integer topicId;
    @Column(name = "topic_name")
    private String topicName;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
