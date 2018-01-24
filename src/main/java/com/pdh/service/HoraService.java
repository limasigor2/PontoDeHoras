package com.pdh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdh.model.Hora;
import com.pdh.repository.HoraDao;

@Service
public class HoraService {

	@Autowired
	private HoraDao repository;
	
	public void save(Hora hora) {
		repository.save(hora);
	}
}
