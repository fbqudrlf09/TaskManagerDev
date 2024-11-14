package com.example.taskmanagerdev.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdatePasswordRequestDto {

    private final String oldPassword;
    private final String newPassword;
}
