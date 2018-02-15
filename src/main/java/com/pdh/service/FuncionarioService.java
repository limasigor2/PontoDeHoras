package com.pdh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdh.model.Funcionario;
import com.pdh.repository.FuncionarioDao;

@Service
public class FuncionarioService{
	
	@Autowired
	private FuncionarioDao repository;
	
	public List<Funcionario> findAll(){
		return (List<Funcionario>) repository.findAll();
	}
	
	public Funcionario findOne(Integer id) {
		return repository.findOne(id);
	}
	public void save(Funcionario funcionario) {
		repository.save(funcionario);
	}
	public void delete(Integer id) {
		repository.delete(id);
	}
	public void delete(Funcionario funcionario) {
		repository.delete(funcionario);
	}
	public Funcionario findByUserName(String userName){
		return repository.findByUserName(userName);
	}

}