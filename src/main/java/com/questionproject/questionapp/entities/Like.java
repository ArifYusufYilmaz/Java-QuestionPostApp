package com.questionproject.questionapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name ="likes")
@Data
public class Like {
    @Id
    @Column(name ="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "post_id", nullable = false)
    // bir Post silindiğinde onun tüm like ları da silinsin.
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id", nullable = false)
    // bir user silindiğinde onun tüm like ları da silinsin.
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

}
