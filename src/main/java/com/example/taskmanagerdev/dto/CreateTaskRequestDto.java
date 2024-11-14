package com.example.taskmanagerdev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.validator.constraints.Length;


@Getter
@RequiredArgsConstructor
public class CreateTaskRequestDto {

    @NotNull
    @Length(max = 10)
    private final String title;

    @NotNull
    private final String contents;

    @NotBlank
    @Length(max = 4)
    private final String username;
}
