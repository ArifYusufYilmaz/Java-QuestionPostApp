package com.questionproject.questionapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="posts")
@Data

public class Post {
    @Id
    @Column(name="id")
    private Long id;

    // lazy loading=> post verilerini çektiğimde hemen user bilgisini getirme.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id", nullable = false)
    // bir user silindiğinde onun tüm postları da silinsin.
    @OnDelete(action= OnDeleteAction.CASCADE)
    //serialization(serileştirme)' da problem çıkarmaması için.
    @JsonIgnore
    private User user;

    @Column(name="title")
    private String title;
    @Lob
    @Column(name ="text", columnDefinition="text")
    private String text;
}
