package com.example.taskmanagerdev.repository;

import com.example.taskmanagerdev.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public default Task findTaskByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디를 가진 일정이 없습니다 id = " + id));
    }
}
