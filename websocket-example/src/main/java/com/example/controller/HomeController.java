package com.example.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		model.addAttribute("name", "ggggggggggggggggggg");
		return "home";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(Model model, Principal principal) {
		model.addAttribute("name", principal.getName());
		return "hello";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("name", "ggggggggggggggggggg");
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(Model model, String error, Principal principal) {
		model.addAttribute("name", "ggggggggggggggggggg");
		System.out.println("user name : " + principal.getName());
		return "login";
	}
	
	@GetMapping("/403")
	public String denied403() {
		return "403";
	}
	
	/** hasRole(...) set a prefix for the the content - the default one is ROLE_
	 *  hasAuthority(...) checks the content WITHOUT a prefix, i.e. just the pure content	*/ 
	@PreAuthorize("hasAuthority('ROLE_USER') or hasRole('ADMIN')")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
	

}
