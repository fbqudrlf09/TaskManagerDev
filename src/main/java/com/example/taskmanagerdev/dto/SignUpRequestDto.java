package com.example.taskmanagerdev.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignUpRequestDto {

    private final String username;
    private final String password;
    private final String email;
}
