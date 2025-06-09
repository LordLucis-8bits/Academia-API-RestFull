package com.academia.repository;
import com.academia.model.AulaModel;
import com.academia.model.InstrutorModel;
import com.academia.model.RelatorioModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface RelatorioRepository extends MongoRepository<RelatorioModel, String> {

    //Buscar por aula
    List<RelatorioModel> findByAula(AulaModel aula);

    //Buscar por instrutor 
    List<RelatorioModel> findByInstrutor(InstrutorModel instrutor);

    //Buscar por data de geração
    List<RelatorioModel> findByDataGeracao(LocalDate dataHoraGeracao);
}
