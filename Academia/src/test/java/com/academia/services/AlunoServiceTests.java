package com.academia.services;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.academia.Academia.AcademiaApplication;
import com.academia.enums.AulaStatus;
import com.academia.model.AlunoModel;
import com.academia.model.AulaModel;
import com.academia.model.InstrutorModel;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootTest(classes = AcademiaApplication.class)
public class AlunoServiceTests {

    @Autowired
    private AlunoService alunoService;

    @Test
    public void testMatricularEmAula() {
        AlunoModel aluno = new AlunoModel();
        aluno.setId("1");
        aluno.setNome("João");
        aluno.setIdade(25);
        aluno.setEmail("allyson@hotmail");
        aluno.setSenha("123456");
        aluno.setTipoDeUsuario(com.academia.enums.TipoDeUsuario.ALUNO);

        AulaModel aula = new AulaModel();
        aula.setId("1");
        aula.setTipo("Yoga");
        aula.setHorario(LocalDateTime.now());
        aula.setInstrutor(new InstrutorModel());
        aula.setLimiteAlunos(10);
        aula.setAlunosMatriculados(new ArrayList<>());
        aula.setStatus(AulaStatus.DISPONIVEL);

        AlunoModel alunoMatriculado = alunoService.matricularEmAula(aluno);
        assertNotNull(alunoMatriculado);
        assertEquals("João", alunoMatriculado.getNome());
    }

}