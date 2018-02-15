package com.pdh.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
	@Size(min=5, max=150, message="O nome deve ser entre {min} e {max}.")
	private String nome;

	@Column(name="cpf")
	@NotNull
	private String cpf;
	
	@Column(name="PO")
	@NotNull
	private String PO;
	
	@Column(name="tipo")
	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoDeUsuario tipo;

	@Column(name="senha")
	@NotNull
	private String senha;
	
	@Column(name="user_name")
	@NotNull
	private String userName;
	
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DiaDeTrabalho> diasDeTrabalho = new ArrayList<>();
	
	
	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", PO=" + PO + ", diasDeTrabalho="
				+ diasDeTrabalho +", tipo="+ tipo +", senha="+ senha+"]";
	}

	public Funcionario(String nome, String cpf, String PO, String tipo, String senha, String userName) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setPO(PO);
		this.setTipo(tipo);
		this.setSenha(senha);
		this.setUserName(userName);
		this.diasDeTrabalho = new ArrayList<>();
	}

	public Funcionario() {
		this.diasDeTrabalho = new ArrayList<>();
	}

	public void adicionarDiaDeTrabalho(DiaDeTrabalho diaDeTrabalho) {
		if(this.diasDeTrabalho.equals(null))
			diasDeTrabalho = new ArrayList<>();
		if(!diasDeTrabalho.contains(diaDeTrabalho)) {
			this.diasDeTrabalho.add(diaDeTrabalho);
		}
		diaDeTrabalho.setFuncionario(this);
	}
	public boolean removerDiaDeTrabalho(DiaDeTrabalho diaDeTrabalho) {
		for(DiaDeTrabalho h: diasDeTrabalho)
			if(h.equals(h)) {
				diasDeTrabalho.remove(h);
				return true;
			}
		return false;
	}
	
	public void setDiasDeTrabalho(List<DiaDeTrabalho> lista) {
		this.diasDeTrabalho =lista;
	}
	
	public List<DiaDeTrabalho> getDiasDeTrabalho(){
		return diasDeTrabalho;
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
	public String getPO() {
		return this.PO;
	}
	public void setPO(String PO) {
		this.PO = PO;
	}

	public TipoDeUsuario getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = TipoDeUsuario.valueOf(tipo);
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setTipo(TipoDeUsuario tipo) {
		this.tipo = tipo;
	}
}