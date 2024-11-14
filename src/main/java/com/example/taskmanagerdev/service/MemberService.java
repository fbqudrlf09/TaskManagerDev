package com.example.taskmanagerdev.service;

import com.example.taskmanagerdev.dto.MemberResponseDto;
import com.example.taskmanagerdev.dto.SignUpResponseDto;
import com.example.taskmanagerdev.entity.Member;
import com.example.taskmanagerdev.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto signUp(String username, String password, String email) {
        Member member = new Member(username, password, email);

        Member saveMember = memberRepository.save(member);

        return new SignUpResponseDto(saveMember.getId(), saveMember.getUsername(), saveMember.getEmail());
    }

    public MemberResponseDto findById(Long id) {

        Member findMember = findByIdOrElseThrow(id);
        return new MemberResponseDto(findMember.getUsername(), findMember.getEmail());
    }


    private Member findByIdOrElseThrow(Long id) {
        return memberRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,  "해당 아이디를 찾지 못했습니다. ID = " + id));

    }
}
