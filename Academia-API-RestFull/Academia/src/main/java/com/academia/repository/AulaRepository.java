package com.academia.repository;
import com.academia.model.AulaModel;
import com.academia.model.InstrutorModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AulaRepository extends MongoRepository<AulaModel, String> {
    
    //Buscar por horario
    List<AulaModel> findByHorario(LocalDateTime horario);

    //Buscar por Instrutor
    List<AulaModel> findByInstrutor(InstrutorModel instrutor);
    
    //Buscar por status
    List<AulaModel> findByStatus(String status);

}
