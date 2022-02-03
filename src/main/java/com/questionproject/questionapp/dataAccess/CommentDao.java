package com.questionproject.questionapp.dataAccess;

import com.questionproject.questionapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Long > {

}
