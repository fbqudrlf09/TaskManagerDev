package com.example.taskmanagerdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TaskManagerDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerDevApplication.class, args);
    }

}