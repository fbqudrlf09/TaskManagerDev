package com.example.taskmanagerdev.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class LoginRequestDto {

    @NotBlank
    @Length(max = 4)
    private String username;
    @NotBlank
    private String password;
}
