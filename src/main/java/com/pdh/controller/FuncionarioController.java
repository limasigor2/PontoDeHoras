package com.pdh.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pdh.model.Funcionario;
import com.pdh.model.Hora;
import com.pdh.model.TipoDeHora;
import com.pdh.service.FuncionarioService;
import com.pdh.service.HoraService;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
		
	@Autowired
	private HoraService horaService;
	
	@GetMapping(path="/funcionarios")
	public ModelAndView findAll(Hora hora) {
		ModelAndView mv = new ModelAndView("/funcionarios/listar");
		
		mv.addObject("funcionarios", funcionarioService.findAll());
		
		mv.addObject("hora", hora);
		
		mv.addObject("TiposDeHora",Arrays.asList(TipoDeHora.values()));
		return mv;
	}
	@GetMapping(path="/funcionarios/verhoras/")
	public ModelAndView getHorasByFuncionario(@RequestParam("id") int id, @RequestParam("ano") int ano, @RequestParam("mes") int mes) {
		Funcionario funcionario = funcionarioService.findOne(id);
		ModelAndView mv = new ModelAndView("/funcionarios/verHoras");
		mv.addObject("lista", horaService.getAllByMonthByYearByFuncionario(funcionario, mes, ano));
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
		return add(funcionarioService.findOne(id));
	}
	@GetMapping(path="/funcionarios/delete/{id}")
	public ModelAndView delete(@PathVariable("id") int id) {
		funcionarioService.delete(id);
		return findAll(new Hora());
	}
	@PostMapping(path="/funcionarios/save")
	public ModelAndView save(@Valid Funcionario funcionario, BindingResult result) {
		if(result.hasErrors())
			return findAll(new Hora());
		funcionarioService.save(funcionario);
		return findAll(new Hora());
	}
	@GetMapping(path="/funcionarios/hora/add")
	public ModelAndView addHoraFuncionario() {
		//ModelAndView mv = new ModelAndView("/funcionarios/add_hora");
		return findAll(new Hora());
	}
	@PostMapping(path="/funcionarios/hora/add/{func_id}/{hora}/{dia}/{mes}/{ano}")
	public ModelAndView addHora(@PathVariable("func_id") int func_id, @PathVariable int hora,
								@PathVariable int dia, @PathVariable int mes, @PathVariable int ano){
		return findAll(new Hora());
	}



}
