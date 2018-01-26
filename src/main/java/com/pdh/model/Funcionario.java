package com.pdh.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity(name="funcionario")
public class Funcionario {

	@Id
	@Column(name="func_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="nome")
	@NotNull
	@Size(min=5, max=100, message="O nome deve ser entre {min} e {max}.")
	private String nome;

	@Column(name="cpf")
	@NotNull
	private String cpf;
	
	@OneToMany(mappedBy = "func")
	public List<Hora> horas;

	public Funcionario(String nome, String cpf) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.horas = new ArrayList<>();
	}

	public Funcionario() {
		this.horas = new ArrayList<>();
	}

	
	public void adicionarHora(Hora hora) {
		if(this.horas.equals(null))
			horas = new ArrayList<>();
		this.horas.add(hora);
		hora.setFunc(this);
	}
	public boolean removeHora(Hora hora) {
		for(Hora h: horas)
			if(h.equals(h)) {
				horas.remove(h);
				return true;
			}
		return false;
	}
	
	public void setHoras(List<Hora> lista) {
		this.horas =lista;
	}
	
	public List<Hora> getHoras(){
		return horas;
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
}
