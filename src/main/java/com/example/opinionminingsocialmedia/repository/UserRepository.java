package com.example.opinionminingsocialmedia.repository;

import com.example.opinionminingsocialmedia.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String userName);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    @Query("SELECT u.role.id FROM User u WHERE u.username = :username")
    Integer findRoleIdById(@Param("username") String username);
}
