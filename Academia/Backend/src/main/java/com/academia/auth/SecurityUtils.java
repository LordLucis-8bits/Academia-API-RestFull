package com.academia.auth;

import org.springframework.security.core.context.SecurityContextHolder;

import com.academia.shared.UserModel;

public class SecurityUtils {

    public static UserModel getCurrentUser() {
        return (UserModel) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    }
}