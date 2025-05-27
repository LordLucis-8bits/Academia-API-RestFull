package com.academia.model;
import org.springframework.data.mongodb.core.mapping.Document;
import com.academia.enums.PlanoStatus;
import com.academia.enums.TipoDePlano;
import com.academia.enums.TipoDeUsuario;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "Alunos")
public class AlunoModel extends UsuarioModel {
    private TipoDeUsuario tipoDeUsuario; // Define o tipo de usuário como "Aluno"
    private TipoDePlano tipoDePlano;
    private LocalDateTime dataInicioDePlano;
    private LocalDate dataFimPlano;
    private List<AulaModel> aulasMatriculadas = new ArrayList<>(); // Inicializa a lista de aulas matriculadas
    private PlanoStatus status; //

    public AlunoModel() {super();}
    
    public AlunoModel(String id, String nome, int idade, String email, String senha, TipoDeUsuario tipoDeUsuario, TipoDePlano tipoDePlano, LocalDateTime dataInicioDePlano, LocalDate dataFimPlano,List<AulaModel> aulasMatriculadas, PlanoStatus status) {
        super(id, nome, idade, email, senha, tipoDeUsuario);
        this.tipoDeUsuario = TipoDeUsuario.ALUNO; // Define o tipo de usuário como "Aluno"
        this.tipoDePlano = tipoDePlano;
        this.dataInicioDePlano = dataInicioDePlano;
        this.dataFimPlano = dataFimPlano;
        this.aulasMatriculadas = aulasMatriculadas;
        this.status = status;
    }
    
    //Getters
    public TipoDePlano getTipoDePlano() {return tipoDePlano;}
    public List<AulaModel> getAulasMatriculadas() {return aulasMatriculadas;}
    public LocalDateTime getDataInicioDePlano() {return dataInicioDePlano;}
    public LocalDate getDataFimPlano() {return dataFimPlano;}
    public TipoDeUsuario getTipoDeUsuario() {return tipoDeUsuario;} // Define o tipo de usuário como "Aluno"
    public PlanoStatus getStatus() {return status;} // Define o status do plano como "Ativo" ou "Inativo"

    //Setters
    public void setTipoDePlano(TipoDePlano tipoDePlano) {this.tipoDePlano = tipoDePlano;}
    public void setAulasMatriculadas(List<AulaModel> aulasMatriculadas) {this.aulasMatriculadas = aulasMatriculadas;}
    public void setDataInicioDePlano(LocalDateTime dataInicioDePlano) {this.dataInicioDePlano = dataInicioDePlano;}
    public void setDataFimPlano(LocalDate dataFimPlano) {this.dataFimPlano = dataFimPlano;}
    public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {this.tipoDeUsuario = tipoDeUsuario;} // Define o tipo de usuário como "Aluno"
    public void setStatus(PlanoStatus status) {this.status = status;} // Define o status do plano como "Ativo" ou "Inativo"
  
}    
