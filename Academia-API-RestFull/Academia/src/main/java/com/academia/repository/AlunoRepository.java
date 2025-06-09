package com.academia.repository;
import com.academia.enums.TipoDePlano;
import com.academia.model.AlunoModel;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlunoRepository extends MongoRepository<AlunoModel, String> {

    //Busca por nome
    List<AlunoModel> findByNome(String nome);
 
    //Busca por idade
    List<AlunoModel> findByIdade(int idade);

    //Busca por plano
    List<AlunoModel> findByTipoDePlano(TipoDePlano tipoDePlano);

}
