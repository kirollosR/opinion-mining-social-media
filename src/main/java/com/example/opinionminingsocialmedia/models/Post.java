package com.example.opinionminingsocialmedia.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;


@Entity
@Table(name = "posts")
public class Post {
    public Post() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "content", nullable = false)
    private String content;
    @Lob
    @Transient
    @Column(name = "image", nullable = false)
    private MultipartFile image;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id")
    private Topic topic;
    @Column(name = "post_score")
    private Float postScore;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, name = "created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Timestamp createdAt;
    @Column(name = "post_likes", columnDefinition = "integer default 0")
    private Integer postLikes;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Float getPostScore() {
        return postScore;
    }

    public void setPostScore(Float postScore) {
        this.postScore = postScore;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(Integer postLikes) {
        this.postLikes = postLikes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
