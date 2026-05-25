package com.academia.security;

import org.springframework.security.core.context.SecurityContextHolder;

import com.academia.model.UserModel;

public class SecurityUtils {

    public static UserModel getCurrentUser() {
        return (UserModel) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    }
}