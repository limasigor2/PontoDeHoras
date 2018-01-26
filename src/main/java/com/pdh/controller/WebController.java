package com.pdh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;

import com.pdh.model.Funcionario;
import com.pdh.model.Hora;
import com.pdh.model.TipoDeHora;
import com.pdh.repository.FuncionarioDao;
import com.pdh.repository.HoraDao;

@Controller
public class WebController {

	@Autowired
	private FuncionarioDao funcionarioDao;

	@Autowired
	private HoraDao horaDao;
	
	@RequestMapping(value={"/","home"})
    public String home(){
		Funcionario func = funcionarioDao.getOne(1);
		Hora hora1 = new Hora(TipoDeHora.ENTRADA_MANHA, LocalDateTime.now());
		Hora hora2 = new Hora(TipoDeHora.SAIDA_MANHA, LocalDateTime.now());
		hora1.setId(2);
//		hora2.setId(3);
		System.out.println(func);
		func.adicionarHora(hora1);
		func.adicionarHora(hora2);
		hora1.setFunc(func);
		horaDao.save(hora1);
		funcionarioDao.save(func);
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