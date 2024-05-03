package com.hasdedin.user.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hasdedin.user.entity.BudgetUser;

public class CustomUserDetails implements UserDetails{
	
	private String name;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(BudgetUser userInfo) {
        this.name = userInfo.getName();
        this.password = userInfo.getPassword();
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")); // Default role

        // Check if the user is an admin and assign the admin role if true
        if ("admin".equalsIgnoreCase(userInfo.getName())) {
            this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
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
        return name;
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
