package com.academia.services;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.academia.enums.PlanoStatus;
import com.academia.enums.TipoDePlano;
import com.academia.model.AlunoModel;
import com.academia.model.AulaModel;
import com.academia.model.InstrutorModel;
import com.academia.model.RelatorioModel;
import com.academia.repository.AlunoRepository;
import com.academia.repository.AulaRepository;
import com.academia.repository.InstrutorRepository;
import com.academia.repository.RelatorioRepository;

@Service
public class AdministradorService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private RelatorioRepository relatorioRepository;

    // Métodos para gerenciar alunos, instrutores e aulas

    //Alunos
    public AlunoModel cadastrarAluno(AlunoModel aluno) {
        return alunoRepository.save(aluno);
    }

    public AlunoModel editarAluno(AlunoModel aluno) {
        return alunoRepository.save(aluno);
    }
    
    public void excluirAluno(String id) {
        alunoRepository.deleteById(id);
    }

    public void listarAlunos() {
        alunoRepository.findAll().forEach(System.out::println);
    }


    //Instrutores
    public void cadastrarInstrutor(InstrutorModel instrutor) {
        instrutorRepository.save(instrutor);
    }

    public void editarInstrutor(InstrutorModel instrutor) {
        instrutorRepository.save(instrutor);
    }

    public void excluirInstrutor(String id) {
        instrutorRepository.deleteById(id);
    }

    public void listarInstrutores() {
        instrutorRepository.findAll().forEach(System.out::println);
    }


    //Aulas
    public void cadastrarAula(AulaModel aula) {
        aulaRepository.save(aula);
    }

    public void editarAula(AulaModel aula) {
        aulaRepository.save(aula);
    }

    public void excluirAula(String id) {
        aulaRepository.deleteById(id);
    }    

    public void listarAulas() {
        aulaRepository.findAll().forEach(System.out::println);
    }


    //Relatórios
    public RelatorioModel vizualizarRelatorio(String id) {
        return relatorioRepository.findById(id).orElse(null);
    }

    public List<RelatorioModel> listarRelatorios() {
        return relatorioRepository.findAll();
    }

    public void renovarPlano(AlunoModel aluno, TipoDePlano tipoDePlano, LocalDate novaData){
        aluno.setTipoDePlano(tipoDePlano);
        aluno.setDataFimPlano(novaData);
        aluno.setStatus(PlanoStatus.ATIVO);
        alunoRepository.save(aluno);
    }

}
