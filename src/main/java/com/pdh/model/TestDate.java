package com.pdh.model;

import java.time.LocalDate;
import java.time.LocalTime;


public class TestDate {

	public LocalDate startDate;
	
	public LocalTime endDate;
	
	public static void main(String[] args) {
		TestDate t = new TestDate();
		t.startDate = LocalDate.now();
		t.endDate = LocalTime.now();
		System.out.println(t.startDate);
		System.out.println(t.endDate);

	}
}
