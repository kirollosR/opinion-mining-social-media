package com.example.opinionminingsocialmedia.repositories;

import com.example.opinionminingsocialmedia.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
