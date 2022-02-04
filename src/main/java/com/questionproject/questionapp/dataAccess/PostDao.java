package com.questionproject.questionapp.dataAccess;

import com.questionproject.questionapp.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface PostDao extends JpaRepository<Post, Long> {
    @Transactional
    List<Post> findByUserId(Long userId);
}
