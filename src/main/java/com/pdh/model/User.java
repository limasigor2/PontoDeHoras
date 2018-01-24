package com.pdh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name="usuario")
public class User extends Pessoa{

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
		super(nome, cpf);
		setPassword(password);
		setNomeUsuario(nomeUsuario);
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

		
}
