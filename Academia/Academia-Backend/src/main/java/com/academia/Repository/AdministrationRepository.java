package com.academia.Repository;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.academia.Model.AdministrationModel;

public interface AdministrationRepository extends MongoRepository<AdministrationModel, String> {
    Optional<AdministrationModel> findByName(String name);

    Optional<AdministrationModel> findByEmail(String email);
}
