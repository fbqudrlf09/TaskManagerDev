package com.example.taskmanagerdev.controller;

import com.example.taskmanagerdev.SessionConst;
import com.example.taskmanagerdev.dto.CreateTaskRequestDto;
import com.example.taskmanagerdev.dto.TaskResponseDto;
import com.example.taskmanagerdev.dto.UpdateTaskRequestDto;
import com.example.taskmanagerdev.entity.Member;
import com.example.taskmanagerdev.entity.Task;
import com.example.taskmanagerdev.service.TaskService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
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

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTaskById(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequestDto requestDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long loginId = (Long)session.getAttribute(SessionConst.LOGIN_MEMBER);

        TaskResponseDto responseDto = taskService.update(id, requestDto.getTitle(), requestDto.getContents(), loginId);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long loginId = (Long) session.getAttribute(SessionConst.LOGIN_MEMBER);

        taskService.deleteTaskById(id, loginId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
