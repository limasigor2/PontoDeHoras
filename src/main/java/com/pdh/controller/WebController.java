package com.pdh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class WebController {

	@RequestMapping(path={"/","home"})
    public ModelAndView home(){
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}

    @RequestMapping(path="/login")
    public ModelAndView login(){
        ModelAndView mv = new ModelAndView("login");
    	return mv;
    }
	@RequestMapping(path="/logout")
	public ModelAndView logout(HttpSession session){
		session.invalidate();
        ModelAndView mv = new ModelAndView("login");
		return mv;
	}

    @RequestMapping(path="/403")
    public ModelAndView Error403(){
        return new ModelAndView("403");
    }
}