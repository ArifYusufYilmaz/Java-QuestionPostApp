package com.questionproject.questionapp.business;

import com.questionproject.questionapp.dataAccess.UserDao;
import com.questionproject.questionapp.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserManager {
    private UserDao userDao;

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        // bu user db'mizde olmayabilir.
        // Şimdilik bulamazsa null dönsün.
        // custom exception ekle
        return userDao.findAll();
    }

    public User saveOneUser(User newUser) {
        return userDao.save(newUser);
    }

    public User getOneUserById(Long userId) {
        //custom exception
        return userDao.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userDao.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userDao.save(foundUser);
            return foundUser;
        } else{
            //custom exception ekle
            return null;
        }
    }

    public void deleteById(Long userId) {
        userDao.deleteById(userId);
    }

    public User getOneUserByUserName(String userName) {
        return userDao.findByUserName(userName);
    }
}
