package com.example.opinionminingsocialmedia.services;

import com.example.opinionminingsocialmedia.core.security.Response;
import com.example.opinionminingsocialmedia.errors.RecordNotFoundException;
import com.example.opinionminingsocialmedia.models.Keyword;
import com.example.opinionminingsocialmedia.models.KeywordGrade;
import com.example.opinionminingsocialmedia.models.User;
import com.example.opinionminingsocialmedia.payload.requests.KeywordRequest;
import com.example.opinionminingsocialmedia.payload.responses.JWTResponse;
import com.example.opinionminingsocialmedia.payload.responses.KeywordResponse;
import com.example.opinionminingsocialmedia.payload.responses.UserResponse;
import com.example.opinionminingsocialmedia.repository.KeywordRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class KeywordService {
    @Autowired
    private KeywordRepository keywordRepository;
    @Autowired
    UserServices userServices;
    @Autowired
    KeywordGradeService keywordGradeService;

    public Response getAllKeywords (HttpServletRequest request){
        if(!userServices.isAdmin(request)){
            return Response
                    .builder()
                    .success(false)
                    .message("Sorry you are not authorized")
                    .build();
        }
        List<KeywordResponse> keywordResponses = new ArrayList<>();
        List<Keyword> keywords = keywordRepository.findAll();
        for(Keyword keyword : keywords){
            User user = keyword.getUser();
            UserResponse userResponse = UserResponse.builder()
                    .username(user.getUsername())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .role(user.getRole().getName())
                    .build();
            KeywordResponse keywordResponse = KeywordResponse.builder()
                    .id(keyword.getId())
                    .name(keyword.getName())
                    .score(keyword.getScore())
                    .user(userResponse)
                    .build();
            keywordResponses.add(keywordResponse);
        }
        if(keywordResponses.isEmpty()){
            return Response.builder()
                    .success(true)
                    .message("Keywords not found")
                    .data(null)
                    .build();
        } else{
            return Response.builder()
                    .success(true)
                    .message("Keywords found")
                    .data(keywordResponses)
                    .build();
        }
    }

    public Response getKeywordById (int id, HttpServletRequest request) {
        try {
            if(!userServices.isAdmin(request)){
                return Response
                        .builder()
                        .success(false)
                        .message("Sorry you are not authorized")
                        .build();
            }
            Keyword keyword = keywordRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Keyword not found"));
            return Response.builder()
                    .success(true)
                    .message("Keyword found")
                    .data(keyword)
                    .build();
        } catch (RecordNotFoundException e) {
            return Response.builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    public Response getKeywordByName(String name){
        try {
            Keyword keyword = keywordRepository.findByName(name).orElseThrow(() -> new RecordNotFoundException("Keyword not found"));
            return Response.builder()
                    .success(true)
                    .message("Keyword found")
                    .data(keyword)
                    .build();
        } catch (RecordNotFoundException e) {
            return Response.builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    public Response addKeyword(KeywordRequest keywordRequest, HttpServletRequest request){
        if(!userServices.isAdmin(request)){
            return Response
                    .builder()
                    .success(false)
                    .message("Sorry you are not authorized")
                    .build();
        }
        Integer userId = keywordRequest.getUserID();
        var user = userServices.findById(userId);
        Integer score = keywordRequest.getScore();
        var keywordGrade = keywordGradeService.findById(keywordRequest.getScore());
        if(user.isPresent()){
            Keyword keyword = Keyword.builder()
                    .name(keywordRequest.getName())
                    .score(keywordGrade.get())
                    .user(user.get())
                    .build();
            return Response.builder()
                    .message("Keyword added successfully")
                    .data(keywordRepository.save(keyword))
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

    public Response deleteKeyword (int id, HttpServletRequest request){
        try {
            if(!userServices.isAdmin(request)){
                return Response
                        .builder()
                        .success(false)
                        .message("Sorry you are not authorized")
                        .build();
            }
            keywordRepository.deleteById(id);
            return Response.builder()
                    .success(true)
                    .message("Keyword deleted successfully")
                    .data(null)
                    .build();
        } catch (Exception e){
            return Response.builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    public Response updateKeyword(KeywordRequest keywordRequest, int id, HttpServletRequest request){
        try {
            if(!userServices.isAdmin(request)){
                return Response
                        .builder()
                        .success(false)
                        .message("Sorry you are not authorized")
                        .build();
            }
            Optional<Keyword> keyword = keywordRepository.findById(id);
            KeywordGrade keywordGrade = keywordGradeService.findById(keywordRequest.getScore()).get();
            if(keyword.isPresent()){
                keyword.get().setName(keywordRequest.getName());
                keyword.get().setScore(keywordGrade);
                keywordRepository.save(keyword.get());
                return Response.builder()
                        .success(true)
                        .message("Keyword updated successfully")
                        .data(keyword)
                        .build();
            } else {
                return Response.builder()
                        .success(false)
                        .message("Keyword not found")
                        .data(null)
                        .build();
            }
        } catch (Exception e){
            return Response.builder()
                    .success(false)
                    .message(e.getMessage())
                    .data(null)
                    .build();
        }
    }

    public List<Object> findByUserId (Integer userId){
        return keywordRepository.findByUserId(userId);
    }
}
