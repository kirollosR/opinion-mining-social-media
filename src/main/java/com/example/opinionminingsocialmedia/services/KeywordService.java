package com.example.opinionminingsocialmedia.services;

import com.example.opinionminingsocialmedia.core.security.Response;
import com.example.opinionminingsocialmedia.errors.RecordNotFoundException;
import com.example.opinionminingsocialmedia.models.Keyword;
import com.example.opinionminingsocialmedia.models.User;
import com.example.opinionminingsocialmedia.repositories.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KeywordService {
    @Autowired
    private KeywordRepository keywordRepository;
    @Autowired
    UserServices userServices;

    public List<Keyword> getAllKeywords (){
        return keywordRepository.findAll();
    }

    public Response getKeywordById (int id) {
        try {
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

    public Response addKeyword(Keyword keyword){
        Integer userId = keyword.getUser().getId();
        if(userServices.isUserIdValid(userId)){
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

    public List<Object> findByUserId (Integer userId){
        return keywordRepository.findByUserId(userId);
    }
}
