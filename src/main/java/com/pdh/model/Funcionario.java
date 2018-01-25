package com.pdh.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity(name="funcionario")
public class Funcionario extends Pessoa {

	public Funcionario(String nome, String cpf) {
		super(nome, cpf);
		this.horas = new ArrayList<>();
	}

	
	public Funcionario() {
		super();
		this.horas = new ArrayList<>();
		super.setNome("");
		super.setCpf("");
	}

	@OneToMany(cascade = CascadeType.ALL,
    		fetch = FetchType.LAZY,
    		mappedBy = "func")
	public List<Hora> horas;
	
	public void adicionarHora(Hora hora) {
		this.horas.add(hora);
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
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < horas.size();i++) {
			str += horas.get(i).toString() + "\n";
		}
		return "Funcionario [horas=" + str + ", nome=" +  super.getNome()+", cpf=" +super.getCpf() +"]";
	}


}
