package com.questionproject.questionapp.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name ="comments")
@Data
public class Comment {
    @Id
    @Column(name ="comment_id")
    private Long id;
    @Column(name= "post_id")
    private Long postId;
    @Column(name="user_id")
    private Long userId;
    @Lob // ?
    @Column(name="text", columnDefinition = "text")
    //Stringi varchar(255) olarak değil de text olarak algılaması için definiton ifade edildi.
    private String text;
}
