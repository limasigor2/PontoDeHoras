package com.pdh.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

	public static void main(String[] args) {
//	    LocalDate date = LocalDate.now();
//	    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
//	    String text = date.format(formatters);
//	    LocalDate parsedDate = LocalDate.parse(text, formatters);
//
//	    System.out.println("date: " + date);
//	    System.out.println("Text format " + text);
//	    System.out.println("parsedDate: " + parsedDate.format(formatters));
	
	    LocalDate date = LocalDate.now();
	    DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
	    String text = date.format(formatters);
	    LocalDate parsedDate = LocalDate.parse(text, formatters);

	    System.out.println("date: " + date);
	    System.out.println("Text format " + text);
	    System.out.println("parsedDate: " + parsedDate.format(formatters));


	
	}
}
