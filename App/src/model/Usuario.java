package model;

public class Usuario {

	private int id;
    private String nome;
    private String email;
    private String senha;
    private int acesso;

    public Usuario(int Id, String Nome, String Email, String Senha, int Acesso){
        this.id = Id;
    	this.nome = Nome;
        this.email = Email;
        this.senha = Senha;
        this.acesso = Acesso;
    }

    public int getId() {
    	return id;
    }
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getAcesso() {
        return acesso;
    }
}

