package com.pdh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdh.model.Funcionario;
import com.pdh.model.DiaDeTrabalho;
import com.pdh.repository.DiaDeTrabalhoDao;

@Service
public class DiaDeTrabalhoService {

	@Autowired
	private DiaDeTrabalhoDao repository;
	
	
	public void save(DiaDeTrabalho diaDeTrabalho) {
		repository.save(diaDeTrabalho);
	}
	public List<DiaDeTrabalho> getAllByMonthByYearByFuncionario(Funcionario funcionario, int month, int year){
		List<DiaDeTrabalho> listaHoras = new ArrayList<>();
		for(DiaDeTrabalho diaDeTrabalho : funcionario.getDiasDeTrabalho()) {
			if(diaDeTrabalho.getDate().getYear() == year)
				if(diaDeTrabalho.getDate().getMonthValue() == month)
					listaHoras.add(diaDeTrabalho);
		}
		return listaHoras;
	}
}
