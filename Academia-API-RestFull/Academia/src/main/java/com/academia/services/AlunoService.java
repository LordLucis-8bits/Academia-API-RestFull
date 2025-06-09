package com.academia.services;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academia.enums.AulaStatus;
import com.academia.enums.PlanoStatus;
import com.academia.enums.TipoDePlano;
import com.academia.model.AlunoModel;
import com.academia.model.AulaModel;
import com.academia.repository.AlunoRepository;
import com.academia.repository.AulaRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AulaRepository aulaRepository;
    
    public AlunoModel matricularEmAula(AlunoModel aluno, AulaModel aula) {

        if (aluno == null || aula == null) {
            throw new IllegalArgumentException("Aluno ou aula não podem ser nulos.");
        }
        if (aluno.getAulasMatriculadas().contains(aula)) {
            throw new IllegalArgumentException("Aluno já está matriculado nesta aula.");
        }
        if (aula.getAlunosMatriculados().size() >= aula.getLimiteAlunos()) {
            throw new IllegalArgumentException("Limite de aulas matriculadas atingido.");
        }
        aluno.getAulasMatriculadas().add(aula);
        aula.getAlunosMatriculados().add(aluno);

        alunoRepository.save(aluno);
        aulaRepository.save(aula);
        return aluno;
    }

    protected LocalDate calcularDataFimPlano(TipoDePlano tipoDePlano, LocalDateTime dataInicioDePlano) {
        LocalDate dataFimPlano = null;
        switch (tipoDePlano) {
            case DIARIO:
                dataFimPlano = dataInicioDePlano.toLocalDate().plusDays(1);
                break;
            case MENSAL:
                dataFimPlano = dataInicioDePlano.toLocalDate().plusMonths(1);
                break;
            case TRIMESTRAL:
                dataFimPlano = dataInicioDePlano.toLocalDate().plusMonths(3);
                break;
            case SEMESTRAL:
                dataFimPlano = dataInicioDePlano.toLocalDate().plusMonths(6);
                break;
            default:
                throw new IllegalArgumentException("Tipo de plano inválido: " + tipoDePlano);                     
    }

        return dataFimPlano;
    }

    public PlanoStatus verificarEstadoPlano(AlunoModel aluno) {
        LocalDate dataAtual = LocalDate.now();
        if (aluno.getDataFimPlano().isBefore(dataAtual)) {
            return PlanoStatus.INATIVO;
        } else {
            return PlanoStatus.ATIVO;
        }

    }

    public boolean verificarEstaMatriculado(AlunoModel aluno, String id) {
        for (AulaModel aula : aluno.getAulasMatriculadas()) {
            if (aula.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public List<AulaModel> visualizarAulasDisponiveis() {
    List<AulaModel> aulasDisponiveis = aulaRepository.findAll();

    return aulasDisponiveis.stream()
        .filter(aula -> aula.getAlunosMatriculados().size() < aula.getLimiteAlunos())
        .filter(aula -> aula.getStatus() == AulaStatus.DISPONIVEL)
        .toList();
    }
    
}
