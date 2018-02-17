package com.pdh.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.text.DecimalFormat;

import org.springframework.format.annotation.DateTimeFormat;


@Entity(name="dia_de_trabalho")
public class DiaDeTrabalho {

	@Column(name="id")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;
    
    @Column(name="date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public LocalDate date;
    
    @Column(name="entrada")
	public LocalTime entrada;

    @Column(name="saida_Almoco")
	public LocalTime saidaAlmoco;
    
    @Column(name="entrada_Almoco")
	public LocalTime entradaAlmoco;
    
    @Column(name="saida")
	public LocalTime saida;
	
	public String getTempoTrabalhado() {
		long minutosTotais = ChronoUnit.MINUTES.between(this.getEntrada(), this.getSaidaAlmoco()) + ChronoUnit.MINUTES.between(this.getEntradaAlmoco(), this.getSaida());
    	int horasTrabalhadas = (int) (minutosTotais/60);
    	int minutosTrabalhados = (int) (minutosTotais % 60);
    	return horasTrabalhadas + ":" + new DecimalFormat("00").format(minutosTrabalhados);
    }

	public DiaDeTrabalho(Integer id, Funcionario funcionario, LocalTime entrada,
					LocalTime saidaAlmoco, LocalTime entradaAlmoco, LocalTime saida) {
		this.setId(id);
		this.setFuncionario(funcionario);
		this.setEntrada(entrada);
		this.setEntradaAlmoco(entradaAlmoco);
		this.setSaidaAlmoco(saidaAlmoco);
		this.setSaida(saida);
    }
    public DiaDeTrabalho(Funcionario funcionario, LocalTime entrada,
					LocalTime saidaAlmoco, LocalTime entradaAlmoco, LocalTime saida) {
	this.setFuncionario(funcionario);
	this.setEntrada(entrada);
	this.setEntradaAlmoco(entradaAlmoco);
	this.setSaidaAlmoco(saidaAlmoco);
	this.setSaida(saida);
}
	public LocalTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalTime entrada) {
		this.entrada = entrada;
	}

	public LocalTime getSaidaAlmoco() {
		return saidaAlmoco;
	}

	public void setSaidaAlmoco(LocalTime saidaAlmoco) {
		this.saidaAlmoco = saidaAlmoco;
	}

	public LocalTime getEntradaAlmoco() {
		return entradaAlmoco;
	}

	public void setEntradaAlmoco(LocalTime entradaAlmoco) {
		this.entradaAlmoco = entradaAlmoco;
	}

	public LocalTime getSaida() {
		return saida;
	}

	public void setSaida(LocalTime saida) {
		this.saida = saida;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate entrada) {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
		this.date = LocalDate.parse(entrada.format(formatters), formatters);
	}

	public DiaDeTrabalho() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    @Override
	public String toString() {
		return "DiaDeTrabalho [id=" + id + ", funcionario=" + funcionario + ", date=" + date + ", entrada=" + entrada
				+ ", saidaAlmoco=" + saidaAlmoco + ", entradaAlmoco=" + entradaAlmoco + ", saida=" + saida + "]";
	}

}
