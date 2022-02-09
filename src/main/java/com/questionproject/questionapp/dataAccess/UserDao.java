package com.questionproject.questionapp.dataAccess;

import com.questionproject.questionapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
