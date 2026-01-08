package com.academia.Repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Enum.UserType;
import com.academia.Model.UserModel;

public interface UserRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);

    List<UserModel> findByUserType(UserType userType);

    boolean existsByEmail(String email);
}
