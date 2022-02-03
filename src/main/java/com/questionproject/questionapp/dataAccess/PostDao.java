package com.questionproject.questionapp.dataAccess;

import com.questionproject.questionapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Post, Long> {
}
