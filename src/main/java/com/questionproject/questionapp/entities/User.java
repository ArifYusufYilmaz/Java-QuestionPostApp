package com.questionproject.questionapp.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @Column(name="user_id")
   private Long id;
    @Column(name="user_name")
   private String userName;
    @Column(name="user_password")
   private String password;
}
