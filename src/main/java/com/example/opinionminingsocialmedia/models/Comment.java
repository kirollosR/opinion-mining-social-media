package com.example.opinionminingsocialmedia.models;

import jakarta.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    @Column(name = "comment_score")
    private Integer commentScore;
    @Column(name = "comment_date")
    private Date commentDate;
    @Column(name = "comment_data")
    private String commentData;
    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @ManyToOne(cascade = CascadeType.ALL)
    private Topic topic;

    public Comment(Integer commentId, Integer commentScore, Date commentDate, String commentData, Post post, User user, Topic topic) {
        this.commentId = commentId;
        this.commentScore = commentScore;
        this.commentDate = commentDate;
        this.commentData = commentData;
        this.post = post;
        this.user = user;
        this.topic = topic;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(Integer commentScore) {
        this.commentScore = commentScore;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentData() {
        return commentData;
    }

    public void setCommentData(String commentData) {
        this.commentData = commentData;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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
}
