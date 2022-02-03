package com.questionproject.questionapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="posts")
@Data

public class Post {
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="user_id")
    private Long userId;
    @Column(name="post_id")
    private Long postId;
    @Lob
    @Column(name ="text", columnDefinition="text")
    private String text;
}
