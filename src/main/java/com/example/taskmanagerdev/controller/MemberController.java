package com.example.taskmanagerdev.controller;

import com.example.taskmanagerdev.dto.SignUpRequestDto;
import com.example.taskmanagerdev.dto.SignUpResponseDto;
import com.example.taskmanagerdev.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> singUp(@RequestBody SignUpRequestDto requestDto ) {

        SignUpResponseDto signUpResponseDto = memberService.signUp(
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getEmail()
        );


        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }
}
