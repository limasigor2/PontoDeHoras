package com.pdh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class WebController {

	@RequestMapping(path={"/","home"})
    public ModelAndView home(){
		return new ModelAndView("login");
	}

    @RequestMapping(path="/login")
    public ModelAndView login(){
        return new ModelAndView("login");
    }
	@RequestMapping(path="/logout")
	public ModelAndView logout(HttpSession session){
		session.invalidate();
        return new ModelAndView("login");
	}

    @RequestMapping(path="/403")
    public ModelAndView Error403(HttpSession session){
        return new ModelAndView("403");
    }
}