package com.questionproject.questionapp.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="likes")
@Data
public class Like {
    @Id
    @Column(name ="id")
    private Long id;
    @Column(name= "post_id")
    private Long postId;
    @Column(name="user_id")
    private Long userId;
}
