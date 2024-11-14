package com.example.taskmanagerdev.repository;

import com.example.taskmanagerdev.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MemberRepository extends JpaRepository<Member, Long> {
}
