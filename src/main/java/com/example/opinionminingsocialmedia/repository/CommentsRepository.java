package com.example.opinionminingsocialmedia.repository;

import com.example.opinionminingsocialmedia.models.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findAll(Pageable pageable);

    Page<Comment> findByPostId(Integer postId,Pageable pageable);
}
