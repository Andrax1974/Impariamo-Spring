package com.xantrix.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController
{
	@RequestMapping
	public String getWelcome(Model model)
	{
		model.addAttribute("intestazione", "Benvenuti nel sito Alpha Shop");
		model.addAttribute("saluti", "Accedi o registrati per acquistare i tuoi prodotti");
		
		return "index";
	}
	
}
