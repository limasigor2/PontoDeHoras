package com.pdh.model;

import java.util.Calendar;

public class Hora {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		System.out.println("Data Agora: "+ c.getTime());
		System.out.println("Ano: " + c.get(Calendar.YEAR));
		System.out.println("Mes: " + c.get(Calendar.MONTH));
		System.out.println("Dia: " + c.get(Calendar.DAY_OF_YEAR));
		
	}
}
