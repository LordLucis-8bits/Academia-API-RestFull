package com.academia.services;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academia.model.AulaModel;
import com.academia.model.RelatorioModel;
import com.academia.repository.RelatorioRepository;

@Service
public class RelatorioService {

    @Autowired
    private RelatorioRepository relatorioRepository;

    //Gerar relatorio automaticamente quando finalizar aula
    public RelatorioModel gerarRelatorio(AulaModel aula) {
        RelatorioModel relatorio = new RelatorioModel();
        relatorio.setDataHoraGeracao(LocalDateTime.now());
        relatorio.setInstrutor(aula.getInstrutor());
        relatorio.setAula(aula);
        relatorio.setAlunos(aula.getAlunosMatriculados());

        return relatorioRepository.save(relatorio);
    }
    
    public List<RelatorioModel> listarRelatorios() {
        return relatorioRepository.findAll();
    }

    public RelatorioModel buscarRelatorioPorId(String id) {
        return relatorioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Relatório não encontrado"));
    }	

}