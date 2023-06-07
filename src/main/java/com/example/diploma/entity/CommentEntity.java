package com.example.diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    @Column(name = "user_id")
    private int author;
    @Column(name = "created_at")
    private long createdAt;
    @Column(name = "comments_text")
    private String text;
}
