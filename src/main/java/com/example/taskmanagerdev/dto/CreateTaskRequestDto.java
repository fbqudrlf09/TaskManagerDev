package com.example.taskmanagerdev.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateTaskRequestDto {

    private final String title;
    private final String contents;
    private final String username;
}
