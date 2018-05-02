package com.xantrix.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/promo")
public class PromoController
{
	@RequestMapping
	public String getPromo(Model model)
	{
		return "Promo";
	}
	
	@RequestMapping(value = "/{filter}", method = RequestMethod.GET)
	public String GestDettPromo(@PathVariable("filter") String Filter, Model model)
	{
		model.addAttribute("Titolo", "Gestione Promozione");
		model.addAttribute("IdPromo", Filter);
		return "GestPromo";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String GestNewPromo(Model model)
	{
		model.addAttribute("Titolo", "Creazione Nuova Promozione");
		model.addAttribute("IdPromo", "");
		return "GestPromo";
	}
}
