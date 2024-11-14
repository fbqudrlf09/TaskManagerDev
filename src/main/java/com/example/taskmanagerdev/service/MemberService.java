package com.example.taskmanagerdev.service;

import com.example.taskmanagerdev.dto.SignUpResponseDto;
import com.example.taskmanagerdev.entity.Member;
import com.example.taskmanagerdev.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String username, String password, String email) {
        Member member = new Member(username, password, email);

        Member saveMember = memberRepository.save(member);

        return new SignUpResponseDto(saveMember.getId(), saveMember.getUsername(), saveMember.getEmail());
    }
}
