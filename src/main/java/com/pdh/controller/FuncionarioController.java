package com.pdh.controller;


import java.util.Arrays;

import javax.servlet.http.HttpSession;
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
import com.pdh.model.TipoDeUsuario;
import com.pdh.service.DiaDeTrabalhoService;
import com.pdh.service.FuncionarioService;
import com.pdh.util.ValidadorDePermissao;

@Controller
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
		
	@Autowired
	private DiaDeTrabalhoService diaDeTrabalhoService;
	
	@Autowired
	private ValidadorDePermissao validadorDePermissao;
	
	@GetMapping(path="/funcionarios")
	public ModelAndView findAll(DiaDeTrabalho diaDeTrabalho, HttpSession session) {

		if(!validadorDePermissao.temPermissao((Funcionario) session.getAttribute("usuario"), "/funcionarios/VerEntradasEditavel")) {
			session.invalidate();
			ModelAndView mv = new ModelAndView("/login");
			mv.addObject("msg", "Sem permissao para adicionar um usuário no sistema");
			return mv;
		}
		ModelAndView mv = new ModelAndView("funcionarios/listar");
		mv.addObject("msg","");
		mv.addObject("funcionarios", funcionarioService.findAll());
		mv.addObject("diaDeTrabalho", diaDeTrabalho);
		mv.addObject("TipoDeUsuario",Arrays.asList(TipoDeUsuario.values()));

		return mv;
	}
	@GetMapping(path="/funcionarios/verentradas")
	public ModelAndView getDiasDeTrabalhoByFuncionario(@RequestParam("id") int id, @RequestParam("ano") int ano, @RequestParam("mes") int mes) {
		
		Funcionario funcionario = funcionarioService.findOne(id);
		ModelAndView mv = new ModelAndView("funcionarios/verentradas");
		mv.addObject("msg","");
		mv.addObject("funcionario", funcionario);
		mv.addObject("lista", diaDeTrabalhoService.getAllByMonthByYearByFuncionario(funcionario, mes, ano));
		mv.addObject("total", diaDeTrabalhoService.getAllTempoDeServicoByMonthByYearByFuncionario(funcionario, mes, ano));
		return mv;
	}
	@GetMapping(path="/funcionarios/VerEntradasEditavel")
	public ModelAndView getDiasDeTrabalhoByFuncionarioEditavel(@RequestParam("id") int id, @RequestParam("ano") int ano, @RequestParam("mes") int mes, HttpSession session) {
		Funcionario funcionario = funcionarioService.findOne(id);
		if(!validadorDePermissao.temPermissao((Funcionario) session.getAttribute("usuario"), "/funcionarios/VerEntradasEditavel")) {
			session.invalidate();
			ModelAndView mv = new ModelAndView("/login");
			mv.addObject("msg", "Sem permissao para acessar essa página");
			return mv;
		}
		ModelAndView mv = new ModelAndView("/funcionarios/ver_entradas_editavel");
		mv.addObject("msg","");
		mv.addObject("funcionario", funcionario);
		mv.addObject("lista", diaDeTrabalhoService.getAllByMonthByYearByFuncionario(funcionario, mes, ano));
		mv.addObject("total", diaDeTrabalhoService.getAllTempoDeServicoByMonthByYearByFuncionario(funcionario, mes, ano));
		return mv;
	}
	
	@GetMapping(path="/funcionarios/add")
	public ModelAndView add(Funcionario funcionario, HttpSession session) {
		System.out.println(funcionario);
		System.out.println(funcionario.getTipo());
		System.out.println(validadorDePermissao.equals(null));
		if(validadorDePermissao.temPermissao(funcionario, "/funcionarios/VerEntradasEditavel") == false) {
			System.out.println("Entrei no if");
			session.invalidate();
			ModelAndView mv = new ModelAndView("/login");
			mv.addObject("msg", "Sem permissao para adicionar um usuário no sistema");
			return mv;
		}
		System.out.println("Não entrei do if");
		ModelAndView mv = new ModelAndView("/funcionarios/add");
		mv.addObject("funcionario", funcionario);
		mv.addObject("TipoDeUsuario",Arrays.asList(TipoDeUsuario.values()));
		return mv;
	}
	@GetMapping(path="/funcionarios/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id, HttpSession session) {
		if(!validadorDePermissao.temPermissao((Funcionario) session.getAttribute("usuario"), "/funcionarios/VerEntradasEditavel")) {
			session.invalidate();
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("msg", "Sem permissao para adicionar um usuário no sistema");
			return mv;
		}
		return add(funcionarioService.findOne(id), session);
	}
	@GetMapping(path="/funcionarios/delete/{id}")
	public ModelAndView delete(@PathVariable("id") int id, HttpSession session) {
		if(!validadorDePermissao.temPermissao((Funcionario) session.getAttribute("usuario"), "/funcionarios/VerEntradasEditavel")) {
			session.invalidate();
			ModelAndView mv = new ModelAndView("login");
			mv.addObject("msg", "Sem permissao para adicionar um usuário no sistema");
			return mv;
		}
		funcionarioService.delete(id);
		ModelAndView mv = new ModelAndView("/funcionarios/listar");
		mv.addObject("funcionarios", funcionarioService.findAll());
		mv.addObject("diaDeTrabalho", new DiaDeTrabalho());
		mv.addObject("msg", "funcionario excluido com sucesso");
		return mv;
	}
	@PostMapping(path="/funcionarios/save")
	public ModelAndView save(@Valid Funcionario funcionario, BindingResult result, HttpSession session) {
		if(!validadorDePermissao.temPermissao((Funcionario) session.getAttribute("usuario"), "/funcionarios/VerEntradasEditavel")) {
			session.invalidate();
			ModelAndView mv = new ModelAndView("/login");
			mv.addObject("msg", "Sem permissao para adicionar um usuário no sistema");
			return mv;
		}

		ModelAndView mv = new ModelAndView("funcionarios/listar");
		if(result.hasErrors()) {
			mv.addObject("msg", "Não foi possível salvar ou atualizar o funcionário");
			return findAll(new DiaDeTrabalho(), session);
		}
		if(funcionario.getNome().length() < 5) {
			mv.addObject("msg", "Nome muito pequeno");
			return findAll(new DiaDeTrabalho(), session);
		}else if(funcionario.getNome().length() > 150) {
			mv.addObject("msg", "Nome muito grande");
			return findAll(new DiaDeTrabalho(), session);
		}
		if(funcionario.getCpf() == null) {
			mv.addObject("msg", "Não foi possível salvar o funcionário, pois o CPF estava vazio");
			return findAll(new DiaDeTrabalho(), session);
		}
		if(funcionario.getPO() == null) {
			mv.addObject("msg", "Não foi possível salvar o funcionário, pois o PO estava vazio");
			return findAll(new DiaDeTrabalho(), session);
		}
		if(funcionario.getTipo() == null) {
			mv.addObject("msg", "Não foi possível salvar o funcionário, pois o Tipo estava vazio");
			return findAll(new DiaDeTrabalho(), session);
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
	@PostMapping(path="/login")
	public ModelAndView login(String userName, String senha, HttpSession session){
		ModelAndView mv = new ModelAndView("login");
		Funcionario usuario = funcionarioService.findByUserName(userName);
		System.out.println("userName=" + userName);
		System.out.println(usuario);
		if(usuario != null){
			if(usuario.getSenha().equals(senha)){
				session.setAttribute("usuario", usuario);
				if(usuario.getTipo().equals(TipoDeUsuario.funcionario)) {
					mv = new ModelAndView("funcionarios/home_funcionario");
					mv.addObject("funcionario", usuario);
					mv.addObject("diaDeTrabalho", new DiaDeTrabalho());
					mv.addObject("msg","");
					return mv;
				}
				else if(usuario.getTipo().equals(TipoDeUsuario.administrador)) {
					mv = new ModelAndView("funcionarios/listar");
					mv.addObject("msg","");
					mv.addObject("funcionario", usuario);
					mv.addObject("funcionarios", funcionarioService.findAll());
					mv.addObject("diaDeTrabalho", new DiaDeTrabalho());
					mv.addObject("TipoDeUsuario",Arrays.asList(TipoDeUsuario.values()));
					return mv;
				}
			}
			else{
				mv.addObject("msg", "Senha incorreta.");
				return mv;
			}
		}else{
			mv.addObject("msg", "Senha incorreta.");
			return mv;
		}
		return mv;
	}

}
