package com.example.taskmanagerdev;

import com.example.taskmanagerdev.entity.Member;
import com.example.taskmanagerdev.entity.Task;
import com.example.taskmanagerdev.repository.MemberRepository;
import com.example.taskmanagerdev.repository.TaskRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final TaskRepository taskRepository;

    @PostConstruct
    public void Init() {
        Member member1 = new Member("tes1", "tes1", "test@naver.com");
        Member member2 = new Member("tes2", "tes2", "tes2@naver.com");
        Member member3 = new Member("tes3", "tes3", "tes2@naver.com");
        Member member4 = new Member("tes3", "tes3", "tes2@naver.com");

        Task task1 = new Task("task1", "taskContents1", member1);
        Task task2 = new Task("task2", "taskContents2", member1);
        Task task3 = new Task("task3", "taskContents3", member2);
        Task task4 = new Task("task4", "taskContents4", member3);
        Task task5 = new Task("task5", "taskContents5", member3);
        Task task6 = new Task("task6", "taskContents6", member4);

        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        taskRepository.save(task1);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
        taskRepository.save(task5);
        taskRepository.save(task6);

    }
}
