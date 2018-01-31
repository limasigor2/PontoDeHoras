package com.pdh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdh.model.Funcionario;
import com.pdh.model.Hora;
import com.pdh.repository.HoraDao;

@Service
public class HoraService {

	@Autowired
	private HoraDao repository;
	
	
	public void save(Hora hora) {
		repository.save(hora);
	}
	public List<Hora> getAllByMonthByYearByFuncionario(Funcionario funcionario, int month, int year){
		List<Hora> listaHoras = new ArrayList<>();
		for(Hora hora : funcionario.getHoras()) {
			if(hora.getDate().getYear() == year) {
				if(hora.getDate().getMonthValue() == month) {
					listaHoras.add(hora);
				}
			}
		}
		return listaHoras;
	}
}
