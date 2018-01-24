package com.pdh.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hora {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	@Column
	private LocalDateTime localDateTime;
	
    @Enumerated(EnumType.STRING)
    private String tipo;
	
	public Hora() {
		this.tipo = null;
		this.localDateTime = null;
;
	}
	public Hora(String tipo) {
		this.tipo = tipo;
		this.localDateTime = null;
	}
	
	public Hora(String tipo, int year, int month, int dayOfMonth, int hour, int minute) {
		this.localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
		this.tipo = tipo;
	}
	
	public Hora(String tipo, LocalDateTime localDateTime) {
		this.tipo = tipo;
		this.localDateTime = localDateTime;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime horario) {
		this.localDateTime = horario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public static void main(String[] args) {
		Hora hora = new Hora("sainda", 2005,10,25,4,40);
		System.out.println(hora.getLocalDateTime());
	}
}
