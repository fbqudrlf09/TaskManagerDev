package com.example.taskmanagerdev.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "task")
@Getter
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Task(){}

    public Task(String title, String contents, Member member) {
        this.title = title;
        this.contents = contents;
        this.member = member;
    }

    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
