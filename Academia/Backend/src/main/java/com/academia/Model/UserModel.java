package com.academia.model;

import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.academia.enums.UserType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Users")
public class UserModel implements UserDetails {
    
    @Id
    private String id; 

    @NotNull
    private String name;

    @NotNull
    private int age;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private UserType role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
    
    @Override
    public String getUsername() { return email; }
    
    @Override 
    public boolean isAccountNonExpired() { return true; }
    
    @Override
    public boolean isAccountNonLocked() { return true; }
   
    @Override
    public boolean isEnabled() { return true; }
}
