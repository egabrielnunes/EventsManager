package Negocio;

import java.util.Date;


public class Usuario {
	
	private String nome;
	private String end;
	private String cpf;
	private String login;
	private String senha;
	private String email;
	private Date data;
	private String foto;
	
	
	
	public Usuario(String nome, String cpf, String login, Date data) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.login = login;
		this.data = data;
	}
	
	public Usuario(String nome, String email, String cpf) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
	}
	public Usuario() {
		super();
	}
	public Usuario(String nome, String end, String cpf, String login,
			String senha, String email, Date data, String foto) {
		super();
		this.nome = nome;
		this.end = end;
		this.cpf = cpf;
		this.login = login;
		this.senha = senha;
		this.email = email;
		this.data = data;
		this.foto = foto;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	

}
