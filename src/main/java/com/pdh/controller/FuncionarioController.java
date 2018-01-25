package com.pdh.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pdh.model.Funcionario;
import com.pdh.service.FuncionarioService;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
		
	@GetMapping(path="/funcionarios")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("/funcionarios/listar");
		mv.addObject("funcionarios", service.findAll());
		return mv;
	}
	@GetMapping(path="/funcionarios/add")
	public ModelAndView add(Funcionario funcionario) {
		ModelAndView mv = new ModelAndView("/funcionarios/add");
		mv.addObject("funcionario", funcionario);
		return mv;
	}
	@GetMapping(path="/funcionarios/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		return add(service.findOne(id));
	}
	@GetMapping(path="/funcionarios/delete/{id}")
	public ModelAndView delete(@PathVariable("id") int id) {
		service.delete(id);
		return findAll();
	}
	@PostMapping(path="/funcionarios/save")
	public ModelAndView save(@Valid Funcionario funcionario, BindingResult result) {
		if(result.hasErrors())
			return findAll();
		service.save(funcionario);
		return findAll();
	}



}
