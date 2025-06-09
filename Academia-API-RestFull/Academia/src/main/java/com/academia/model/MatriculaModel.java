package com.academia.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(collection = "Matriculas")
public class MatriculaModel {
    @Id
    private String id;
    private LocalDate dataMatricula;
    private AlunoModel aluno;
    private AulaModel aula;

    public MatriculaModel() {}

    public MatriculaModel(String id, LocalDate dataMatricula, AlunoModel aluno, AulaModel aula) {
        this.id = id;
        this.dataMatricula = dataMatricula;
        this.aluno = aluno;
        this.aula = aula;
    }

    // Getters
    public String getId() {return id;}
    public LocalDate getDataMatricula() {return dataMatricula;}
    public AlunoModel getAluno() {return aluno;}
    public AulaModel getAula() {return aula;}

    // Setters
    public void setId(String id) {this.id = id;}
    public void setDataMatricula(LocalDate dataMatricula) {this.dataMatricula = dataMatricula;}
    public void setAluno(AlunoModel aluno) {this.aluno = aluno;}
    public void setAula(AulaModel aula) {this.aula = aula;}
    
}
