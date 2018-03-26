package com.xantrix.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xantrix.webapp.dao.Utenti;
import com.xantrix.webapp.service.UtentiService;

@Controller
public class IndexController
{
	@Autowired
	private UtentiService utentiService;
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index()
	{
		return "index";
	}
	
	@RequestMapping(value = "utenti/cerca", method = RequestMethod.GET)
	public String GetUtentiByFilter(@RequestParam("Par1") String Argomento1, @RequestParam("Par2") String Argomento2, 
			Model model)
	{

		List<Utenti> recordset = utentiService.SelByFilter(Argomento1, Argomento2);

	
		model.addAttribute("Par1", Argomento1);
		model.addAttribute("Par2", Argomento2);
		model.addAttribute("Utenti", recordset);

		return "utenti";
	}
}
