package com.pdh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="usuario")
public class User{

	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nome")
	@NotNull
	@Size(min=5, max=100, message="O nome deve ser entre {min} e {max}.")
	private String nome;
	
	@Column(name="cpf_user")
	@NotNull
	private String cpf;

	@Column
	@NotNull
	@Size(min=6, max=20, message="O nome de usuário deve ser entre {min} e {max}.")
	private String nomeUsuario;
	
	@Column
	@NotNull
	@Size(min=6, max=20, message="A senha deve ser entre {min} e {max}.")
	private String password;
	
	public User() {
		super();
	}
	
	public User(String nome, String cpf, String password, String nomeUsuario) {
		setCpf(cpf);
		setNome(nome);
		setPassword(password);
		setNomeUsuario(nomeUsuario);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", nomeUsuario=" + nomeUsuario + ", password="
				+ password + "]";
	}
		
}
