package com.academia.model;
import org.springframework.data.mongodb.core.mapping.Document;
import com.academia.enums.AulaStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.annotation.Id;

@Document(collection = "Aulas")
public class AulaModel {
    @Id
    private String id;
    private String tipo;
    private LocalDateTime horario;
    private InstrutorModel instrutor;
    private int limiteAlunos;
    private List<AlunoModel> alunosMatriculados; // Inicializa a lista de alunos matriculados
    private AulaStatus status; //"DISPONIVEL", "INDISPONIVEL", "FINALIZADA", "EM_ANDAMENTO", "CANCELADA"

    public AulaModel() {}

    public AulaModel(String id, String tipo, LocalDateTime horario, InstrutorModel instrutor, int limiteAlunos, List<AlunoModel> alunosMatriculados, AulaStatus status) {
        this.id = id;
        this.tipo = tipo;
        this.horario = horario;
        this.instrutor = instrutor;
        this.limiteAlunos = limiteAlunos;
        this.alunosMatriculados = alunosMatriculados;
        this.status = status;
    }

    //Getters
    public String getId() {return id;}       
    public String getTipo() {return tipo;}
    public LocalDateTime getHorario() {return horario;}
    public InstrutorModel getInstrutor() {return instrutor;}
    public int getLimiteAlunos() {return limiteAlunos;}
    public List<AlunoModel> getAlunosMatriculados() {return alunosMatriculados;}
    public AulaStatus getStatus() {return status;}

    //Setters
    public void setId(String id) {this.id = id;}
    public void setTipo(String tipo) {this.tipo = tipo;}
    public void setHorario(LocalDateTime horario) {this.horario = horario;}
    public void setInstrutor(InstrutorModel instrutor) {this.instrutor = instrutor;}
    public void setLimiteAlunos(int limiteAlunos) {this.limiteAlunos = limiteAlunos;}
    public void setAlunosMatriculados(List<AlunoModel> alunosMatriculados) {this.alunosMatriculados = alunosMatriculados;}
    public void setStatus(AulaStatus status) {this.status = status;}
        
}
