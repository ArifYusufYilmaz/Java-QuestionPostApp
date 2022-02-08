package com.questionproject.questionapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_name")
   private String userName;
    @Column(name="user_password")
   private String password;
}
