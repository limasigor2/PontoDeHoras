package com.pdh.model;

import java.util.Calendar;

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
	private Calendar calendar;
	
    @Enumerated(EnumType.STRING)
    private String tipo;
	
	public Hora() {
		this.calendar = Calendar.getInstance();
	}
	public Hora(String tipo) {
		this.tipo = tipo;
		this.calendar = Calendar.getInstance();
	}
	public Hora(Calendar calendar, String tipo) {
		this.calendar = calendar;
		this.tipo = tipo;
	}
	
	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public static void main(String[] args) {
		Hora hora = new Hora();
		System.out.println(hora.getCalendar().getTime());
	}
}
