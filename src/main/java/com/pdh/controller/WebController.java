package com.pdh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pdh.model.Funcionario;
import com.pdh.model.Hora;
import com.pdh.model.TipoDeHora;
import com.pdh.repository.FuncionarioDao;
import com.pdh.repository.HoraDao;


@Controller
public class WebController {

	@Autowired
	private FuncionarioDao funcionarioRepository;
	
	@Autowired
	private HoraDao horaRepository;
	
	@RequestMapping(value={"/","home"})
    public String home(){
		Funcionario func = funcionarioRepository.getOne(2);
		Hora hora = new Hora(TipoDeHora.ENTRADA_MANHA, 01, 1, 11, 2015);
		func.adicionarHora(hora);
		System.out.println(hora);
		System.out.println(func);
		horaRepository.save(hora);
		funcionarioRepository.save(func);
		
		return "home";
    }
    @RequestMapping(value={"/welcome"})
    public String welcome(){
        return "welcome";
    }
  
    @RequestMapping(value="/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping(value={"/login"})
    public String login(){
        return "login";
    }
    
    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }
}