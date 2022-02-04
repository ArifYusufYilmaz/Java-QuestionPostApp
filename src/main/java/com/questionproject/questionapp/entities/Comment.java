package com.questionproject.questionapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name ="comments")
@Data
public class Comment {
    @Id
    @Column(name ="comment_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "post_id", nullable = false)
    // bir Post silindiğinde onun tüm yorumları da silinsin.
    @OnDelete(action= OnDeleteAction.CASCADE)
    @JsonIgnore
    private Post post;


    // lazy loading=> post verilerini çektiğimde hemen user bilgisini getirme.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id", nullable = false)
    // bir user silindiğinde onun tüm yorumları da silinsin.
    @OnDelete(action= OnDeleteAction.CASCADE)
    //serialization(serileştirme)' da problem çıkarmaması için.
    @JsonIgnore
    private User user;


   // @Lob // ?
    @Column(name="text", columnDefinition = "TEXT")
    //Stringi varchar(255) olarak değil de text olarak algılaması için definiton ifade edildi.
    private String text;
}
