package com.questionproject.questionapp.dataAccess;

import com.questionproject.questionapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeDao extends JpaRepository<Like, Long> {

}
