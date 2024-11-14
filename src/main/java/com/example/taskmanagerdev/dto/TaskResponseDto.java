package com.example.taskmanagerdev.dto;

import com.example.taskmanagerdev.entity.Task;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TaskResponseDto {

    private final Long id;
    private final String title;
    private final String contents;

    public static TaskResponseDto toDto(Task task) {
        return new TaskResponseDto(task.getId(), task.getTitle(), task.getContents());
    }
}
