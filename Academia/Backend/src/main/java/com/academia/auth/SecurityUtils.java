package com.academia.auth;

import org.springframework.security.core.context.SecurityContextHolder;

import com.academia.shared.UserModel;

public class SecurityUtils {

    /*quadro de avisos: SecurityContext que guarda o objeto Authentication que guarda dentro de si o 
    principal(usuario em si) e o authorities(permissões) */

    public static UserModel getCurrentUser() {
        return (UserModel) SecurityContextHolder
        .getContext() //pega o "quadro de avisos" atual
        .getAuthentication() //pega a informação de autenticação que foi escrita ali (o objeto Authentication que o JwtFilter criou)
        .getPrincipal(); //pega o "dono" dessa autenticação — no seu caso, o objeto UserDetails/UserModel que foi passado lá no JwtFilter
    }
}