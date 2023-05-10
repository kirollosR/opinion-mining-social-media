package com.example.opinionminingsocialmedia.services;

import com.example.opinionminingsocialmedia.core.security.Response;
import com.example.opinionminingsocialmedia.models.Topic;
import com.example.opinionminingsocialmedia.payload.requests.TopicRequest;
import com.example.opinionminingsocialmedia.models.User;
import com.example.opinionminingsocialmedia.payload.responses.TopicResponse;
import com.example.opinionminingsocialmedia.payload.responses.UserResponse;
import com.example.opinionminingsocialmedia.repository.TopicRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserServices userServices;

    public Response addTopic(TopicRequest topicRequest, HttpServletRequest request) {
        if(!userServices.isAdmin(request)){
            return Response
                    .builder()
                    .success(false)
                    .message("Sorry you are not authorized")
                    .build();
        }
        Integer userId = topicRequest.getUserID();
        var user = userServices.findById(userId);
        if (user.isPresent()) {
            Topic topic = Topic.builder()
                    .name(topicRequest.getName())
                    .user(user.get())
                    .build();
            return Response.builder()
                    .message("Topic added successfully")
                    .data(topicRepository.save(topic))
                    .success(true)
                    .build();
        } else {
            return Response.builder()
                    .message("User not found")
                    .data(null)
                    .success(true)
                    .build();
        }
    }

    public Response getAllTopics (){
        List<TopicResponse> topicDtoList = new ArrayList<>();
        List<Topic> topicList = topicRepository.findAll();
        for (Topic topic : topicList) {
            User user = topic.getUser();
            UserResponse userDto = UserResponse.builder()
                    .username(user.getUsername())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .role(user.getRole().getName())
                    .build();
            TopicResponse topicResponse = TopicResponse.builder()
                    .id(topic.getId())
                    .name(topic.getName())
                    .user(userDto)
                    .build();
            topicDtoList.add(topicResponse);
        }
        if(topicDtoList.isEmpty()){
            return Response.builder()
                    .success(true)
                    .message("No topics found")
                    .data(null)
                    .build();
        } else{
            return Response.builder()
                    .success(true)
                    .message("Topics found")
                    .data(topicDtoList)
                    .build();
        }
    }

    public Response deleteTopic(Integer id, HttpServletRequest request) {
        try {
            if(!userServices.isAdmin(request)){
                return Response
                        .builder()
                        .success(false)
                        .message("Sorry you are not authorized")
                        .build();
            }
            topicRepository.deleteById(id);
            return Response.builder()
                    .success(true)
                    .message("Topic deleted successfully")
                    .data(null)
                    .build();
        } catch (Exception e) {
            return Response.builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    public Optional<Topic> getTopicById(Integer id) {
        return topicRepository.findById(id);
    }

}
