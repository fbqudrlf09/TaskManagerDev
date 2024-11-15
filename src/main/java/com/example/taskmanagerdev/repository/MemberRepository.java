package com.example.taskmanagerdev.repository;

import com.example.taskmanagerdev.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByUsername(String username);

    public default Member findMemberByUsernameOrElseTrow(String username) {
        return findMemberByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 유저이름을 가진 유저는 없습니다 username = " + username)
        );
    }
}
