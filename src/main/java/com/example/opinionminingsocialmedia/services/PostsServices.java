package com.example.opinionminingsocialmedia.services;

import com.example.opinionminingsocialmedia.models.Post;
import com.example.opinionminingsocialmedia.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServices {
    @Autowired
    private PostsRepository postsRepository;

    public Page<Post> getAllPosts(int page, int pageSize) {
        final Pageable pageable = PageRequest.of(page, pageSize);
        return postsRepository.findAll(pageable);
    }

    public Post getPostById(int id) {
        return postsRepository.findById(id).orElseThrow(null);
    }

//    public List<Post> getPostByTopicName(String topicName) {
//        return postsRepository.findByTopicName(topicName);
//    }

    public Post addPost(Post post) {
        return postsRepository.save(post);
    }
}
