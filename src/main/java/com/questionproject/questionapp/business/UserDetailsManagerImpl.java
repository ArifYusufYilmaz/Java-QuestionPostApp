package com.questionproject.questionapp.business;

import com.questionproject.questionapp.dataAccess.UserDao;
import com.questionproject.questionapp.entities.User;
import com.questionproject.questionapp.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsManagerImpl implements UserDetailsService {

    private UserDao userDao;
    public UserDetailsManagerImpl(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);

        return JwtUserDetails.create(user);
    }
    public UserDetails loadUserById(Long id){
        User user = userDao.findById(id).get();
        return JwtUserDetails.create(user);
    }
}
