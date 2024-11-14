package com.example.taskmanagerdev.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@RequiredArgsConstructor
public class SignUpRequestDto {

    @NotBlank
    @Length(max = 4)
    private final String username;

    @NotBlank
    private final String password;

    @Pattern(regexp = "^[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.[a-z]+$")
    private final String email;
}
