package com.example.taskmanagerdev.service;

import com.example.taskmanagerdev.dto.MemberResponseDto;
import com.example.taskmanagerdev.dto.SignUpResponseDto;
import com.example.taskmanagerdev.entity.Member;
import com.example.taskmanagerdev.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 아이디를 찾지 못했습니다. ID = " + id)
        );
    }

    private Member findByUsernameOrElseThrow(String username) {
        return memberRepository.findMemberByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저이름을 찾지 못했습니다. username = " + username)
        );
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {
        Member findMember = findByIdOrElseThrow(id);
        matchMemberAndPassword(findMember, oldPassword);
        findMember.updatePassword(newPassword);
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public Member login(String username, String password) {
        // 유저명으로 해당 멤버 찾기
        Member findMember = findByUsernameOrElseThrow(username);
        // 해당 멤버와 비번이 맞는지 확인 틀릴시 ResponseStatusEx 발생
        matchMemberAndPassword(findMember, password);

        return findMember;
    }

    private static void matchMemberAndPassword(Member findMember, String password) {
        if (!findMember.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "해당 비밀번호가 맞지 않습니다. PASSWORD = " + password);
        }
    }
}
