package com.healthy.security;

import com.healthy.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {

    private final Integer id;
    private final String userName;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Integer id, String userName, String password, Collection<? extends GrantedAuthority> authorities, User user) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }


    public static UserPrincipal create(User user) {
        String roleName = user.getRole().getName().name();
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + roleName);
        return new UserPrincipal(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                Collections.singleton(authority),
                user);
    }

    public Integer getCustomerId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}