package com.academia.repository;
import com.academia.model.AulaModel;
import com.academia.model.InstrutorModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface InstrutorRepository extends MongoRepository<InstrutorModel, String> {

    //Buscar por nome
    List<InstrutorModel> findByNome(String nome);
    
    //Buscar por especialidade
    List<InstrutorModel> findByEspecialidade(String especialidade);

    //Buscar por email
    InstrutorModel findByEmail(String email);

    //Buscar por Aulas Ministradas
    List<InstrutorModel> findByAulasMinistradas(ArrayList<AulaModel> aulasMinistradas);

}
