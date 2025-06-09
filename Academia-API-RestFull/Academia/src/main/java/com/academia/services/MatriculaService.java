package com.academia.services;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academia.enums.AulaStatus;
import com.academia.enums.PlanoStatus;
import com.academia.model.AlunoModel;
import com.academia.model.AulaModel;
import com.academia.model.MatriculaModel;
import com.academia.repository.MatriculaRepository;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    //Matricula aluno so se ele tiver um plano ativo
    public MatriculaModel matricularAluno(AlunoModel aluno, AulaModel aula) {
        //Verifica plano ativo
        if (!aluno.getStatus().equals(PlanoStatus.ATIVO)) {
            throw new RuntimeException("Plano inativo.");
        }

        //Verifica status da aula
         if (aula.getStatus() != AulaStatus.DISPONIVEL) {
            throw new RuntimeException("Aula não está disponível.");
        }

        //Verifica limite de alunos
        if (aula.getAlunosMatriculados().size() >= aula.getLimiteAlunos()) {
            throw new RuntimeException("Limite de alunos atingido.");
        }

        //Verifica se aluno já está matriculado
        if (aula.getAlunosMatriculados().contains(aluno)) {
            throw new RuntimeException("Aluno já está matriculado nesta aula.");
        }

        //criar matricula
        MatriculaModel matricula = new MatriculaModel();
        matricula.setAluno(aluno);
        matricula.setAula(aula);
        matricula.setDataMatricula(LocalDate.now());

        //Atualizar lista de alunos
        aula.getAlunosMatriculados().add(aluno);

        return matriculaRepository.save(matricula);
        
    }
}    
