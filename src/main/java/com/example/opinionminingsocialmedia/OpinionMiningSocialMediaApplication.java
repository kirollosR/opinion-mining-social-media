package com.example.opinionminingsocialmedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OpinionMiningSocialMediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpinionMiningSocialMediaApplication.class, args);
    }

    private void run() {

    }
}
