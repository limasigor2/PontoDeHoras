package com.pdh.model;

import java.time.LocalDateTime;

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

	@Id()
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer id;
	
    @NotNull
	@Column
	private LocalDateTime localDateTime;
	
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoDeHora tipo;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false, insertable=false, updatable=false)
	public Funcionario func;

    public Hora() {
		this.tipo = null;
		this.localDateTime = null;
	}
	
	public Hora(TipoDeHora tipo) {
		this.tipo = tipo;
		this.localDateTime = null;
	}
	
	public Hora(TipoDeHora tipo, int year, int month, int dayOfMonth, int hour, int minute) {
		this.localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
		this.tipo = tipo;
	}
	
	public Hora(TipoDeHora tipo, LocalDateTime localDateTime) {
		this.tipo = tipo;
		this.localDateTime = localDateTime;
	}
	
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime horario) {
		this.localDateTime = horario;
	}

	public TipoDeHora getTipo() {
		return tipo;
	}

	public void setTipo(TipoDeHora tipo) {
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Funcionario getFunc() {
		return func;
	}

	public void setFunc(Funcionario func) {
		this.func = func;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hora other = (Hora) obj;
		if (func == null) {
			if (other.func != null)
				return false;
		} else if (!func.equals(other.func))
			return false;
		if (localDateTime == null) {
			if (other.localDateTime != null)
				return false;
		} else if (!localDateTime.equals(other.localDateTime))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	@Override
	public String toString() {
		System.out.println("Passei na hora");
		return "Hora [id=" + id + ", localDateTime=" + localDateTime + ", tipo=" + tipo  + "]";
	}

}
