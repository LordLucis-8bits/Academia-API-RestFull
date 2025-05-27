package com.academia.model;
import org.springframework.data.mongodb.core.mapping.Document;
import com.academia.enums.TipoDeUsuario;
import java.util.ArrayList;

@Document(collection = "Instrutores")
public class InstrutorModel extends UsuarioModel {
    private String especialidade;
    private ArrayList<AulaModel> aulasMinistradas = new ArrayList<>(); // Inicializa a lista de aulas ministradas
    private TipoDeUsuario tipoDeUsuario; // Define o tipo de usuário como "Instrutor"

    public InstrutorModel() {super();}
    
    public InstrutorModel(String id, String nome, int idade, String email, String senha, TipoDeUsuario tipoDeUsuario , String especialidade, ArrayList<AulaModel> aulasMinistradas) {
        super(id, nome, idade, email, senha, tipoDeUsuario);
        this.tipoDeUsuario = TipoDeUsuario.INSTRUTOR; // Define o tipo de usuário como "Instrutor"
        this.especialidade = especialidade;
        this.aulasMinistradas = aulasMinistradas;
    }
    
    //Getters
    public String getEspecialidade() {return especialidade;}
    public ArrayList<AulaModel> getAulasMinistradas() {return aulasMinistradas;}
    public TipoDeUsuario getTipoDeUsuario() {return tipoDeUsuario;} // Define o tipo de usuário como "Instrutor"

    //Setters
    public void setEspecialidade(String especialidade) {this.especialidade = especialidade;}
    public void setAulasMinistradas(ArrayList<AulaModel> aulasMinistradas) {this.aulasMinistradas = aulasMinistradas;}  
    public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {this.tipoDeUsuario = tipoDeUsuario;} // Define o tipo de usuário como "Instrutor"

}
