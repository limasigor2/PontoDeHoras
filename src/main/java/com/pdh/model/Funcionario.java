package com.pdh.model;

import javax.persistence.Entity;

@Entity(name="funcionario")
public class Funcionario extends Pessoa {

	public Funcionario(String nome, String cpf) {
		super(nome, cpf);
	}
	public Funcionario() {
		super();
	}

}
