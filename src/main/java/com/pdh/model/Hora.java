package com.pdh.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
import org.springframework.format.annotation.DateTimeFormat;


@Entity(name="hora")
public class Hora {

	public Hora() {}

	public Hora(TipoDeHora tipo, Funcionario funcionario, LocalDate date, LocalTime time) {
		super();
		this.tipo = tipo;
		this.funcionario = funcionario;
		this.date = date;
		this.time = time;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id_hora;
	
	
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoDeHora tipo;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    
    @Column(name="data")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    public LocalDate date;
	
    @Column(name="time")
	public LocalTime time;

    
    public Integer getId_hora() {
		return id_hora;
	}

	public void setId_hora(Integer id_hora) {
		this.id_hora = id_hora;
	}

	@Override
	public String toString() {
		if(this.getFuncionario() != null)
			return "Hora [id_hora=" + id_hora + ", tipo=" + tipo + ", funcionario=" + funcionario.getId() + ", date=" + date
				+ ", time=" + time + "]";
		return "Hora [id_hora=" + id_hora + ", tipo=" + tipo + ", funcionario= null" + ", date=" + date
				+ ", time=" + time + "]";
	}

	public TipoDeHora getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeHora tipo) {
		this.tipo = tipo;
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

	public void setDate(LocalDate date) {
	    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
	    this.date = LocalDate.parse(date.format(formatters), formatters);
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

}
