package com.pdh.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pdh.model.User;
import com.pdh.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping(path="/usuarios")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("/usuarios/listar");
		mv.addObject("usuarios", userService.findAll());
		return mv;
	}
	@GetMapping(path="/usuarios/add")
	public ModelAndView add(User user) {
		ModelAndView mv = new ModelAndView("/usuarios/add");
		mv.addObject("user", user);
		return mv;
	}
	@GetMapping(path="/usuarios/edit/{id}")
	public ModelAndView edit(@PathVariable("id") int id) {
		return add(userService.findOne(id));
	}
	@GetMapping(path="/usuarios/delete/{id}")
	public ModelAndView delete(@PathVariable("id") int id) {
		userService.delete(id);
		return findAll();
	}
	@PostMapping(path="/usuarios/save")
	public ModelAndView save(@Valid User user, BindingResult result) {
		if(result.hasErrors())
			return findAll();
		userService.save(user);
		return findAll();
	}
}
