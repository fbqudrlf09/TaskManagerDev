package com.example.taskmanagerdev.service;

import com.example.taskmanagerdev.dto.TaskResponseDto;
import com.example.taskmanagerdev.entity.Member;
import com.example.taskmanagerdev.entity.Task;
import com.example.taskmanagerdev.repository.MemberRepository;
import com.example.taskmanagerdev.repository.TaskRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final MemberRepository memberRepository;
    private final TaskRepository taskRepository;

    public TaskResponseDto save(String title, String contents, String username) {

        Member findMember = memberRepository.findMemberByUsernameOrElseTrow(username);

        Task task = new Task(title, contents, findMember);
        taskRepository.save(task);

        return new TaskResponseDto(task.getId(), task.getTitle(), task.getContents());
    }

    public List<TaskResponseDto> findAll() {
        return taskRepository.findAll().stream().map(TaskResponseDto::toDto).toList();
    }

    public TaskResponseDto findTaskById(Long id) {

        Task findTask = taskRepository.findTaskByIdOrElseThrow(id);

        return new TaskResponseDto(findTask.getId(), findTask.getTitle(), findTask.getContents());
    }

    @Transactional
    public void deleteTaskById(Long id, Long loginId) {
        Task findTask = taskRepository.findTaskByIdOrElseThrow(id);
        checkTaskUpdatePermission(findTask, loginId);

        taskRepository.delete(findTask);
    }

    @Transactional
    public TaskResponseDto update(Long id, String title, String contents, Long loginId) {
        Task findTask = taskRepository.findTaskByIdOrElseThrow(id);
        checkTaskUpdatePermission(findTask, loginId);
        findTask.update(title, contents);

        return new TaskResponseDto(findTask.getId(), findTask.getTitle(), findTask.getContents());
    }

    /**
     * 로그인한 ID와 작성한 일정의 ID가 같은지 확인하는 코드
     * @param task          일정 목록
     * @param loginId       현재 로그인한 ID
     */
    public void checkTaskUpdatePermission(Task task, Long loginId) {
        if (task.getMember().getId() != loginId) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "해당 일정 변경에 대한 권한이 없습니다");
        }
    }
}