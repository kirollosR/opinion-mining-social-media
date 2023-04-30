package com.example.opinionminingsocialmedia.services;

import com.example.opinionminingsocialmedia.core.security.Response;
import com.example.opinionminingsocialmedia.core.security.TokenUtil;
import com.example.opinionminingsocialmedia.models.*;
import com.example.opinionminingsocialmedia.payload.requests.CommentRequest;
import com.example.opinionminingsocialmedia.repository.CommentsRepository;
import com.example.opinionminingsocialmedia.repository.PostsRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServices {
    @Autowired
    private CommentsRepository commentsRepository;
    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserServices userServices;

    @Autowired
    private TopicService topicService;
    @Autowired
    private PostsRepository postsServices;

    @Autowired
    private KeywordService keywordService;

    public ResponseEntity<Response> getAllPostComments(int postId, int page, int pageSize) {
        return ResponseEntity.ok(
                Response.builder()
                        .success(true)
                        .message("Success")
                        .data(commentsRepository.findByPostId(postId, PageRequest.of(page, pageSize)))
                        .build()
        );
    }

    public ResponseEntity<Response> addComment(int postId, CommentRequest commentRequest, HttpServletRequest httpServletRequest) {
        final String authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String token = authHeader.substring("Bearer ".length());
        String username = tokenUtil.getUserNameFromToken(token);
        Optional<Post> post = postsServices.findById(postId);
        if(post.isEmpty()) {
            return ResponseEntity.badRequest().body(Response
                    .builder()
                    .success(false)
                    .message("Sorry the post id you entered is not found")
                    .build());
        }
        Optional<User> user =  userServices.findByUserName(username);
        Optional<Topic> topic =  topicService.getTopicById(post.get().getTopic().getId());
        if(user.isEmpty()) {
            return ResponseEntity.status(401).body(Response
                    .builder()
                    .success(false)
                    .message("Sorry you not authorized")
                    .build());
        }
        if(topic.isEmpty()) {
            return ResponseEntity.badRequest().body(Response
                    .builder()
                    .success(false)
                    .message("Sorry the topic id you entered is not found")
                    .build());
        }
        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .user(user.get())
                .topic(topic.get())
                .post(post.get())
                .score(0)
                .build();
    }

    private Integer calculateCommentScore(CommentRequest request) {
        var sum = 0;
        var count = 0;
        List<Keyword> keywordList =
    }
}
