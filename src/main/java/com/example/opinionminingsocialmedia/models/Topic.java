package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Entity
@Table(name = "topics")
@Builder
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Topic() {
        super();
    }

    public Topic(Integer topicId, String topicName, User user) {
        this.id = topicId;
        this.name = topicName;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer topicId) {
        this.id = topicId;
    }

    public String getName() {
        return name;
    }

    public void setName(String topicName) {
        this.name = topicName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
