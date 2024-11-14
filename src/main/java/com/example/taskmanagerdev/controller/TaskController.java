package com.example.taskmanagerdev.controller;

import com.example.taskmanagerdev.dto.CreateTaskRequestDto;
import com.example.taskmanagerdev.dto.TaskResponseDto;
import com.example.taskmanagerdev.entity.Task;
import com.example.taskmanagerdev.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDto> save(@Valid @RequestBody CreateTaskRequestDto requestDto) {
        TaskResponseDto responseDto = taskService.save(requestDto.getTitle(), requestDto.getContents(), requestDto.getUsername());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> findAll() {
        List<TaskResponseDto> responseDtoList = taskService.findAll();

        return new ResponseEntity<>(responseDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> findTaskById(@PathVariable Long id) {
        TaskResponseDto responseDto = taskService.findTaskById(id);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        taskService.deleteTaskById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
