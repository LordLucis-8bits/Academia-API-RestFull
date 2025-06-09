package com.academia.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academia.enums.AulaStatus;
import com.academia.model.AlunoModel;
import com.academia.model.AulaModel;
import com.academia.model.InstrutorModel;
import com.academia.repository.AulaRepository;
import com.academia.repository.InstrutorRepository;

@Service
public class AulaService {

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private RelatorioService relatorioService;


    public boolean autenticar(String email, String senha) {
        InstrutorModel instrutor = instrutorRepository.findByEmail(email);
        if (instrutor != null && instrutor.getSenha().equals(senha)) {
            return true;
        } else {
            throw new SecurityException("Email ou senha inválidos");
        }
    }
    
    //Iniciar aula
    public void iniciarAula(String id, String email, String senha) {
        if (autenticar(email, senha)) {
            AulaModel aula = aulaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aula não encontrada"));
            aula.setStatus(AulaStatus.EM_ANDAMENTO);
            aulaRepository.save(aula);
        } else {
            throw new SecurityException("Email ou senha inválidos");
        }
    }

    //Finalizar aula 
    public void finalizarAula(String id, String email, String senha) {
        if (autenticar(email, senha)) {
            AulaModel aula = aulaRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Aula não encontrada"));
            aula.setStatus(AulaStatus.FINALIZADA);
            aulaRepository.save(aula);
            relatorioService.gerarRelatorio(aula); // Gera o relatório automaticamente
        } else {
            throw new SecurityException("Email ou senha inválidos");
        }
    }

    public void registrarPresenca(String id, String email, String senha, AlunoModel aluno) {
        if (autenticar(email, senha)) {
            AulaModel aula = aulaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aula não encontrada"));
            if (aula.getAlunosMatriculados().contains(aluno)) {
                // Lógica para registrar presença
            } else {
                throw new IllegalStateException("Aluno não matriculado na aula");
            }
        } else {
            throw new SecurityException("Email ou senha inválidos");
        }
    }
}
