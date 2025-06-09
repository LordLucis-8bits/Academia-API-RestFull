package com.academia.repository;
import com.academia.model.AlunoModel;
import com.academia.model.AulaModel;
import com.academia.model.MatriculaModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatriculaRepository extends MongoRepository<MatriculaModel, String> {

    //Buscar por data matricula
    List<MatriculaModel> findByDataMatricula(LocalDate dataMatricula);

    //Buscar por aluno
    List<MatriculaModel> findByAluno(AlunoModel aluno);

    //Buscar por aula
    List<MatriculaModel> findByAula(AulaModel aula);

}
