package com.example.opinionminingsocialmedia.repository;

import com.example.opinionminingsocialmedia.models.KeywordGrade;
import com.example.opinionminingsocialmedia.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KeywordGradeService {
    @Autowired
    private KeywordGradeRepository repository;

    public Optional<KeywordGrade> findById(Integer id) {
        return repository.findById(id);
    }
}
