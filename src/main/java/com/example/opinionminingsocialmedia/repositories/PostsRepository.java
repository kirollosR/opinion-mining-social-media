package com.example.opinionminingsocialmedia.repositories;

import com.example.opinionminingsocialmedia.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Post, Integer> {
    Page<Post> findAll(Pageable pageable);

//    List<Post> findByTopicName(String topicName);
}
