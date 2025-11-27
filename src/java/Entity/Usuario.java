package Entity; 

import java.io.Serializable;

public class Usuario implements Serializable {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {}

    public Usuario(int idUsuario, String nome, String email, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    public int getId() {
        return this.idUsuario;
    }

    public void setId(int id) {
        this.idUsuario = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public static class UsuarioBuilder{
    private Usuario usu = new Usuario();
    
    // parte mafe
    public UsuarioBuilder comIdUsuario (int idUsuario){
         this.usu.idUsuario = idUsuario;
         return this;
     }
    
    public UsuarioBuilder comNome (String nome){
         this.usu.nome = nome;
         return this;
    }
    
    public UsuarioBuilder comEmail (String email){
         this.usu.email = email;
         return this;
    }
    
    public UsuarioBuilder comSenha (String senha){
         this.usu.senha = senha;
         return this;
    }
    }
}