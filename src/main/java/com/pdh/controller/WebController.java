package com.pdh.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {
	@RequestMapping(path={"/","home", "login"})
    public String home(){
		return "login";
	}
	@RequestMapping(path="/logout")
	public String logout(HttpSession session){
		session.invalidate();
        return "redirect:/login";
	}
    @RequestMapping(path="/403")
    public ModelAndView Error403(HttpSession session){
        return new ModelAndView("403");
    }
}