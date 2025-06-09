package com.academia.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "Relatorios")
public class RelatorioModel {
    @Id
    private String id;
    private AulaModel aula;
    private InstrutorModel instrutor;
    private List<AlunoModel> alunos;
    private LocalDateTime dataHoraGeracao;

    public RelatorioModel() {}

    public RelatorioModel(String id, AulaModel aula, InstrutorModel instrutor, List<AlunoModel> alunos, LocalDateTime dataHoraGeracao) {
        this.id = id;
        this.aula = aula;
        this.instrutor = instrutor;
        this.alunos = alunos;
        this.dataHoraGeracao = dataHoraGeracao;
    }

    // Getters
    public String getId() {return id;}
    public AulaModel getAula() {return aula;}
    public InstrutorModel getInstrutor() {return instrutor;}
    public List<AlunoModel> getAlunos() {return alunos;}
    public LocalDateTime getDataHoraGeracao() {return dataHoraGeracao;}
    
    // Setters
    public void setId(String id) {this.id = id;}
    public void setAula(AulaModel aula) {this.aula = aula;}
    public void setInstrutor(InstrutorModel instrutor) {this.instrutor = instrutor;}
    public void setAlunos(List<AlunoModel> alunos) {this.alunos = alunos;}
    public void setDataHoraGeracao(LocalDateTime dataHoraGeracao) {this.dataHoraGeracao = dataHoraGeracao;}

}
