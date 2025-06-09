package com.academia.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.academia.enums.TipoDeUsuario;

@Document(collection = "Usuarios")
public abstract class UsuarioModel {
    @Id
    private String id;
    private String nome;
    private int idade;
    private String email;
    private String senha;
    protected TipoDeUsuario tipoDeUsuario; // Pode ser "Aluno", "Instrutor" ou "Administrador"     

    public UsuarioModel() {}

    public UsuarioModel(String id, String nome, int idade, String email, String senha, TipoDeUsuario tipoDeUsuario) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.tipoDeUsuario = tipoDeUsuario;
    }
    
    //Getters
    public String getId() {return id;}
    public String getNome() {return nome;}
    public int getIdade() {return idade;}
    public String getEmail() {return email;}
    public String getSenha() {return senha;}
    public TipoDeUsuario getTipoDeUsuario() {return tipoDeUsuario;}

    //Setters
    public void setId(String id) {this.id = id;}
    public void setNome(String nome) {this.nome = nome;}
    public void setIdade(int idade) {this.idade = idade;}
    public void setEmail(String email) {this.email = email;}
    public void setSenha(String senha) {this.senha = senha;}
    public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {this.tipoDeUsuario = tipoDeUsuario;}

    //Metodo de Autenticação
    public boolean autenticar(String email, String senha) {
        return this.email.equals(email) && this.senha.equals(senha);
    }           
    
}

