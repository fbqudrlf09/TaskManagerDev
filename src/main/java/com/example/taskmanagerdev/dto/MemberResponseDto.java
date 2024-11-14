package com.example.taskmanagerdev.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberResponseDto {

    private final String username;
    private final String email;
}
