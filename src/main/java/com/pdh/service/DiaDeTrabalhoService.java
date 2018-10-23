package com.pdh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdh.model.Funcionario;
import com.pdh.model.DiaDeTrabalho;
import com.pdh.repository.DiaDeTrabalhoRepository;

@Service
public class DiaDeTrabalhoService {

	@Autowired
	private DiaDeTrabalhoRepository diadDeTrabalhoRepository;
	
	public DiaDeTrabalho findOne(int id) {
		return diadDeTrabalhoRepository.findOne(id);
	}
	public void delete(int id) {
		System.out.println(id);
		diadDeTrabalhoRepository.delete(id);
	}
	public void save(DiaDeTrabalho diaDeTrabalho) {
		diadDeTrabalhoRepository.save(diaDeTrabalho);
	}
	public List<DiaDeTrabalho> getAllByMonthByYearByFuncionario(Funcionario funcionario, int mes, int ano){
		List<DiaDeTrabalho> listaHoras = new ArrayList<>();
		for(DiaDeTrabalho diaDeTrabalho : funcionario.getDiasDeTrabalho()) {
			if(diaDeTrabalho.getDate().getYear() == ano 
					&& diaDeTrabalho.getDate().getMonthValue() == mes) {
					listaHoras.add(diaDeTrabalho);
			}
		}
		return listaHoras;
	}
	public String getAllTempoDeServicoByMonthByYearByFuncionario(Funcionario funcionario, int mes, int ano) {
		int totalHoras = 0;
		int totalMinutos = 0;
		for(DiaDeTrabalho diaDeTrabalho : funcionario.getDiasDeTrabalho()) {
			if(diaDeTrabalho.date.getYear() == ano) {
				if(diaDeTrabalho.date.getMonthValue() == mes) {
					String[] tempoDeTrabalho = diaDeTrabalho.getTempoTrabalhado().split(":");
					totalHoras += Integer.parseInt(tempoDeTrabalho[0]);
					totalMinutos += Integer.parseInt(tempoDeTrabalho[1]);
				}
			}
		}
		totalHoras += (int) (totalMinutos/60);
		totalMinutos = totalMinutos % 60;
		return totalHoras + ":" + totalMinutos;
	}
}
