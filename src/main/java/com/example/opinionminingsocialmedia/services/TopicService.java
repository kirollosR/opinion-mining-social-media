package com.example.opinionminingsocialmedia.services;

import com.example.opinionminingsocialmedia.core.security.Response;
import com.example.opinionminingsocialmedia.models.Topic;
import com.example.opinionminingsocialmedia.Dtos.TopicDto;
import com.example.opinionminingsocialmedia.models.User;
import com.example.opinionminingsocialmedia.Dtos.UserDto;
import com.example.opinionminingsocialmedia.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private UserServices userServices;

    public Response addTopic(Topic topic) {
        Integer userId = topic.getUser().getId();
        if (userServices.isUserIdValid(userId)) {
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
        List<TopicDto> topicDtoList = new ArrayList<>();
        List<Topic> topicList = topicRepository.findAll();
        for (Topic topic : topicList) {
            User user = topic.getUser();
            UserDto userDto = UserDto.builder()
                    .username(user.getUsername())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .role(user.getRole().name())
                    .build();
            TopicDto topicDto = TopicDto.builder()
                    .id(topic.getId())
                    .name(topic.getName())
                    .user(userDto)
                    .build();
            topicDtoList.add(topicDto);
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

    public Response deleteTopic(Integer id) {
        try {
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

}
