package com.example.opinionminingsocialmedia.repository;

import com.example.opinionminingsocialmedia.models.Keyword;
import com.example.opinionminingsocialmedia.models.KeywordGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordGradeRepository extends JpaRepository<KeywordGrade, Integer> {

}
