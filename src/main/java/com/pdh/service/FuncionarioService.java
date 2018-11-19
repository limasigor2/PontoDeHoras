package com.pdh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdh.model.Funcionario;
import com.pdh.repository.FuncionarioRepository;

@Service
public class FuncionarioService{
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public List<Funcionario> findAll(){
		return (List<Funcionario>) funcionarioRepository.findAll();
	}
	public Funcionario findOne(Integer id) {
		return funcionarioRepository.findOne(id);
	}
	public void save(Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}
	public void delete(Integer id) {
		funcionarioRepository.delete(id);
	}
	public void delete(Funcionario funcionario) {
		funcionarioRepository.delete(funcionario);
	}
	public Funcionario findByUserName(String userName){
		return funcionarioRepository.findByUserName(userName);
	}

}