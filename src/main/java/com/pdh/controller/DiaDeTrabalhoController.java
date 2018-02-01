package com.pdh.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pdh.model.Funcionario;
import com.pdh.model.DiaDeTrabalho;
import com.pdh.service.FuncionarioService;
import com.pdh.service.DiaDeTrabalhoService;

@Controller
public class DiaDeTrabalhoController {

	@Autowired
	private DiaDeTrabalhoService diaDeTrabalhoService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private FuncionarioController funcionarioController;
	
	@GetMapping("/diaDeTrabalho/add")
	public ModelAndView addHora(DiaDeTrabalho diaDeTrabalho) {
		ModelAndView mv = new ModelAndView("/funcionarios/listar");
		mv.addObject("diaDeTrabalho", diaDeTrabalho);
		return mv;
	}
	@PostMapping("/diaDeTrabalho/save")
	public ModelAndView save(@Valid DiaDeTrabalho diaDeTrabalho, BindingResult result) {
		if(result.hasErrors()) {
			funcionarioController.findAll(new DiaDeTrabalho());
		}
		Funcionario func = diaDeTrabalho.getFuncionario();
		diaDeTrabalhoService.save(diaDeTrabalho);
		funcionarioService.save(func);
		return funcionarioController.findAll(new DiaDeTrabalho());
	}
}
