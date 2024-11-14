package com.example.taskmanagerdev.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdatePasswordRequestDto {

    @NotBlank
    private final String oldPassword;

    @NotBlank
    private final String newPassword;
}
