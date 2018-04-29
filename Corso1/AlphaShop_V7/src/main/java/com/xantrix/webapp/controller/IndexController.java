package com.xantrix.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xantrix.webapp.config.security.SpringSecurityUserContext;

@Controller
@RequestMapping("/")
public class IndexController
{
	private SpringSecurityUserContext userContext;
	
	@RequestMapping
	public String getWelcome(Model model)
	{
		userContext = new SpringSecurityUserContext();
		
		String CurrentUser = (userContext != null) ?  userContext.getCurrentClient() : null;
		
		model.addAttribute("intestazione", "Benvenuti nel sito Alpha Shop");
		model.addAttribute("saluti", "Accedi o registrati per acquistare i tuoi prodotti");
		model.addAttribute("User", CurrentUser);

		
		return "index";
	}
	
}
