package com.academia.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.academia.shared.UserModel;
import com.academia.shared.UserRepository;
import com.academia.shared.enums.UserType;

@Configuration
public class AdminSeeder {

    @Value("${admin.seed.email}")
    private String adminEmail;

    @Value("${admin.seed.password}")
    private String adminPassword;

    @Bean
    public CommandLineRunner seedAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByEmail("admin@academia.com")) {
                UserModel admin = new UserModel();
                admin.setName("Admin");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode(adminPassword)); // Hashing the password before saving
                admin.setAge(20);
                admin.setRole(UserType.ADMIN);

                userRepository.save(admin);
            }
        };
    }
}
