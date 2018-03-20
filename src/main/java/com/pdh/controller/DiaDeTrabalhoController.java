package com.pdh.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String addDiaDeTrabalho(DiaDeTrabalho diaDeTrabalho, RedirectAttributes redirectAttrs) {
		ModelAndView mv = new ModelAndView("funcionarios/listar");
		mv.addObject("diaDeTrabalho", diaDeTrabalho);
		mv.addObject("funcionarios", funcionarioService.findAll());
		//return mv;
		redirectAttrs.addFlashAttribute("diaDeTrabalho", diaDeTrabalho);
		redirectAttrs.addFlashAttribute("funcionarios", funcionarioService.findAll());
		return "redirect:/funcionarios";
	}
	
	@PostMapping("/diaDeTrabalho/save")
	public String save(@Valid DiaDeTrabalho diaDeTrabalho, BindingResult result, HttpSession session, RedirectAttributes redirectAttrs) {
		ModelAndView mv = new ModelAndView("funcionarios/listar");

		if(result.hasErrors()) {
			mv.addObject("msg", "Não foi possível adicionar o dia de trabalhao ao funcionário selecionado");
			funcionarioController.findAll(new DiaDeTrabalho(), session);
		}
		Funcionario func = diaDeTrabalho.getFuncionario();
		
		diaDeTrabalhoService.save(diaDeTrabalho);
		funcionarioService.save(func);
		mv.addObject("funcionarios", funcionarioService.findAll());
		mv.addObject("diaDeTrabalho", diaDeTrabalho);
		mv.addObject("msg", "Horas adicionada ao funcionário com sucesso");
		
		//redirectAttrs.addFlashAttribute(arg0)
		return "redirect:/funcionarios";
	}
	@PostMapping("/diaDeTrabalho/saveAndReturn")
	public ModelAndView saveAndRedirectToFuncionarioPage(@Valid DiaDeTrabalho diaDeTrabalho, BindingResult result, HttpSession session) {
		ModelAndView mv = new ModelAndView("funcionarios/home_funcionario");
		if(result.hasErrors()) {
			mv.addObject("msg", "Não foi possível adicionar o dia de trabalhao ao funcionário selecionado");
			funcionarioController.findAll(new DiaDeTrabalho(), session);
		}
		Funcionario func = diaDeTrabalho.getFuncionario();
		diaDeTrabalhoService.save(diaDeTrabalho);
		funcionarioService.save(func);
		mv.addObject("funcionario", diaDeTrabalho.getFuncionario());
		mv.addObject("funcionarios", funcionarioService.findAll());
		mv.addObject("diaDeTrabalho", diaDeTrabalho);
		mv.addObject("msg", "Horas adicionada ao funcionário com sucesso");
		
		return mv;
	}
	@GetMapping(path="/diaDeTrabalho/delete/{id}")
	public ModelAndView delete(@PathVariable("id") int id) {
		Funcionario func = funcionarioService.findOne(diaDeTrabalhoService.findOne(id).getFuncionario().getId());
		int mes = diaDeTrabalhoService.findOne(id).date.getMonthValue();
		int ano = diaDeTrabalhoService.findOne(id).date.getYear();
		diaDeTrabalhoService.delete(id);
		
		ModelAndView mv = new ModelAndView("/funcionarios/ver_entradas_editavel");
		mv.addObject("funcionario", func);
		mv.addObject("lista", diaDeTrabalhoService.getAllByMonthByYearByFuncionario(func, mes, ano));
		mv.addObject("total", diaDeTrabalhoService.getAllTempoDeServicoByMonthByYearByFuncionario(func, mes, ano));
		
		return mv;
	}

}
