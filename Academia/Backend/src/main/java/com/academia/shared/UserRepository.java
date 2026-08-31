package com.academia.shared;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.academia.shared.enums.UserType;

public interface UserRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);

    List<UserModel> findByRole(UserType userType);

    boolean existsByEmail(String email);

}
