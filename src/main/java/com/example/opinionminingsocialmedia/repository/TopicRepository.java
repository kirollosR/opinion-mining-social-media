package com.example.opinionminingsocialmedia.repository;

import com.example.opinionminingsocialmedia.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
