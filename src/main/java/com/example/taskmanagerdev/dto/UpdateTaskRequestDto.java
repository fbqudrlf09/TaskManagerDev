package com.example.taskmanagerdev.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@RequiredArgsConstructor
public class UpdateTaskRequestDto {

    @NotNull
    @Length(max = 10)
    private final String title;

    @NotNull
    private final String contents;
}
