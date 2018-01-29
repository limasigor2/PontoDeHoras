package com.pdh.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity(name="hora")
public class Hora {

@Override
	public String toString() {
		return "Hora [id_hora=" + id_hora + ", tipo=" + tipo + ", minuto=" + minuto + ", dia=" + dia + ", mes=" + mes
				+ ", ano=" + ano + ", funcionario=" + funcionario.getId() + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id_hora;
	
	
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoDeHora tipo;

    @NotNull
    @Column
    private int minuto;
    
    @NotNull
    @Column
    private int dia;

    @NotNull
    @Column
    private int mes;
    
    @NotNull
    @Column
    private int ano;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    
    public Integer getId_hora() {
		return id_hora;
	}

	public void setId_hora(Integer id_hora) {
		this.id_hora = id_hora;
	}

	public TipoDeHora getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeHora tipo) {
		this.tipo = tipo;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Hora() {}

	public Hora(TipoDeHora tipo, int minuto, int dia, int mes, int ano) {
		super();
		this.tipo = tipo;
		this.minuto = minuto;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
	}
	
	public Hora(TipoDeHora tipo, int minuto, int dia, int mes, int ano, Funcionario funcionario) {
		super();
		this.tipo = tipo;
		this.minuto = minuto;
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.funcionario = funcionario;
	}
    
}
