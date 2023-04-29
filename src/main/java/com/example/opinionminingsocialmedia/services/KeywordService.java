package com.example.opinionminingsocialmedia.services;

import com.example.opinionminingsocialmedia.models.Keyword;
import com.example.opinionminingsocialmedia.repository.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordService {
    @Autowired
    private KeywordRepository keywordRepository;

    public List<Keyword> getAllKeywords (){
        return keywordRepository.findAll();
    }

    public Keyword getKeywordById (int id){
        return keywordRepository.findById(id).orElseThrow(null);
    }

    public Keyword addKeyword(Keyword keyword){
        return keywordRepository.save(keyword);
    }

    public List<Keyword> findByUserId (Integer userId){
        return keywordRepository.findByUserId(userId);
    }
}
