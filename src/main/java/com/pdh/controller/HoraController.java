package com.pdh.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pdh.model.Funcionario;
import com.pdh.model.Hora;
import com.pdh.service.FuncionarioService;
import com.pdh.service.HoraService;

@Controller
public class HoraController {

	@Autowired
	private HoraService horaService;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private FuncionarioController funcionarioController;
	
	@GetMapping("/hora/add")
	public ModelAndView addHora(Hora hora) {
		ModelAndView mv = new ModelAndView("/funcionarios/listar");
		mv.addObject("hora", hora);
		return mv;
	}
	@PostMapping("/hora/save")
	public ModelAndView save(@Valid Hora hora, BindingResult result) {
		if(result.hasErrors()) {
			funcionarioController.findAll(new Hora());
		}
		Funcionario func = hora.getFuncionario();
		horaService.save(hora);
		funcionarioService.save(func);
		funcionarioController.findAll(new Hora());
		return null;
	}
}
