package com.example.taskmanagerdev.service;

import com.example.taskmanagerdev.dto.TaskResponseDto;
import com.example.taskmanagerdev.entity.Member;
import com.example.taskmanagerdev.entity.Task;
import com.example.taskmanagerdev.repository.MemberRepository;
import com.example.taskmanagerdev.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final MemberRepository memberRepository;
    private final TaskRepository taskRepository;

    public TaskResponseDto save(String title, String contents, String username) {

        Member findMember = findMemberByUsernameOrElseThrow(username);

        Task task = new Task(title, contents, findMember);
        taskRepository.save(task);

        return new TaskResponseDto(task.getId(), task.getTitle(), task.getContents());
    }

    public List<TaskResponseDto> findAll() {
        return taskRepository.findAll().stream().map(TaskResponseDto::toDto).toList();
    }

    public TaskResponseDto findTaskById(Long id) {

        Task findTask = findTaskByIdOrElseThrow(id);

        return new TaskResponseDto(findTask.getId(), findTask.getTitle(), findTask.getContents());
    }

    private Member findMemberByUsernameOrElseThrow(String username) {
        return memberRepository.findMemberByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저이름을 가진 유저는 없습니다 username = " + username)
        );
    }

    private Task findTaskByIdOrElseThrow(Long id) {
        return taskRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디를 가진 일정이 없습니다 id = " + id)
        );
    }

    public void deleteTaskById(Long id) {
        Task findTask = findTaskByIdOrElseThrow(id);
        taskRepository.delete(findTask);
    }
}
