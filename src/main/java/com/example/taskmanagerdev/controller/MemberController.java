package com.example.taskmanagerdev.controller;

import com.example.taskmanagerdev.SessionConst;
import com.example.taskmanagerdev.dto.*;
import com.example.taskmanagerdev.entity.Member;
import com.example.taskmanagerdev.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> singUp(@Valid @RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto = memberService.signUp(
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getEmail()
        );
        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request) {

        // 해당 아이디와 비번 확인
        Member loginMember = memberService.login(requestDto.getUsername(), requestDto.getPassword());

        // 맞는 회원일 경우 세션에 추가
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {

        // 세션을 제거
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id) {
        MemberResponseDto memberResponseDto = memberService.findById(id);

        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @Valid @RequestBody UpdatePasswordRequestDto requestDto, HttpServletRequest request) {
        checkAuthorized(request, id, "해당 아이디에 대한 변경할 권한이 없습니다");
        memberService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id, HttpServletRequest request) {
        checkAuthorized(request, id, "해당 아이디에 대한 삭제할 권한이 없습니다");
        memberService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private static void checkAuthorized(HttpServletRequest request, Long id, String message) {
        HttpSession session = request.getSession();
        if (session.getAttribute(SessionConst.LOGIN_MEMBER) != id) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, message);
        }
    }
}
