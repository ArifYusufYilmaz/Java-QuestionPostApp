package com.questionproject.questionapp.security;


import com.questionproject.questionapp.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
// spring security sadece username ve passwordun olduğu user objesi yaratmamızı istiyor.

@Getter
@Setter
public class JwtUserDetails implements UserDetails {
    public Long id;
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private JwtUserDetails(Long id, String userName, String password, Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }
    public static JwtUserDetails create(User user){
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(),authoritiesList);
    }


    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;    //
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;   //
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;    //
    }

    @Override
    public boolean isEnabled() {
        return true;   //
    }
}