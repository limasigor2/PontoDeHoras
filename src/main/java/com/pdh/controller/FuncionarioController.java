package com.pdh.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pdh.model.DiaDeTrabalho;
import com.pdh.model.Funcionario;
import com.pdh.service.DiaDeTrabalhoService;
import com.pdh.service.FuncionarioService;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
		
	@Autowired
	private DiaDeTrabalhoService diaDeTrabalhoService;
	
	@GetMapping(path="/funcionarios")
	public ModelAndView findAll(DiaDeTrabalho diaDeTrabalho) {
		ModelAndView mv = new ModelAndView("/funcionarios/listar");
		mv.addObject("msg", "");
		mv.addObject("funcionarios", funcionarioService.findAll());
		mv.addObject("diaDeTrabalho", diaDeTrabalho);
		
		return mv;
	}
	@GetMapping(path="/funcionarios/verentradas/")
	public ModelAndView getDiasDeTrabalhoByFuncionario(@RequestParam("id") int id, @RequestParam("ano") int ano, @RequestParam("mes") int mes) {
		Funcionario funcionario = funcionarioService.findOne(id);
		ModelAndView mv = new ModelAndView("/funcionarios/verEntradasEOuSaidas");
		mv.addObject("funcionario", funcionario);
		mv.addObject("lista", diaDeTrabalhoService.getAllByMonthByYearByFuncionario(funcionario, mes, ano));
		mv.addObject("total", diaDeTrabalhoService.getAllTempoDeServicoByMonthByYearByFuncionario(funcionario, mes, ano));
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
		ModelAndView mv = new ModelAndView("/funcionarios/listar");
		
		mv.addObject("funcionarios", funcionarioService.findAll());
		mv.addObject("diaDeTrabalho", new DiaDeTrabalho());
		mv.addObject("msg", "funcionario excluido com sucesso");
		return mv;
	}
	
	@PostMapping(path="/funcionarios/save")
	public ModelAndView save(@Valid Funcionario funcionario, BindingResult result) {
		ModelAndView mv = new ModelAndView("/funcionarios/listar");

		if(result.hasErrors()) {
			mv.addObject("msg", "Não foi possível salvar ou atualizar o funcionário");
			return findAll(new DiaDeTrabalho());
		}
			
		if(funcionario.getId() == null) {
			funcionarioService.save(funcionario);
			mv.addObject("msg", "funcionario adicionado com sucesso");
		}else {
			funcionarioService.save(funcionario);
			mv.addObject("msg", "funcionario atualizado com sucesso");
		}
		
		mv.addObject("funcionarios", funcionarioService.findAll());
		mv.addObject("diaDeTrabalho", new DiaDeTrabalho());
		
		return mv;
	}
	
	@GetMapping(path="/funcionarios/entradaousaida/add")
	public ModelAndView addDiaDeTrabalhoFuncionario() {
		return findAll(new DiaDeTrabalho());
	}

}
