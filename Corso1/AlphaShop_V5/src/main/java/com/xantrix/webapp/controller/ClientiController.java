package com.xantrix.webapp.controller;

 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xantrix.webapp.entities.Clienti;
import com.xantrix.webapp.entities.Utenti;
import com.xantrix.webapp.service.ClientiService;

@Controller
@RequestMapping("/clienti")
public class ClientiController
{
	@Autowired
	private ClientiService clientiService;
	
	private List<Clienti> recordset;

	// http://localhost:8080/alphashop/clienti/cerca/
	@RequestMapping(value = "/cerca/{filter}", method = RequestMethod.GET)
	public String GetClientiByFilter(@PathVariable("filter") String Filter, Model model)
	{
		Clienti cliente = clientiService.SelCliente(Filter);

		/*
		model.addAttribute("NumArt", NumArt);
		model.addAttribute("Titolo", "Ricerca Articoli");
		model.addAttribute("Titolo2", "Risultati Ricerca " + Filter);
		model.addAttribute("Articoli", recordset);
		*/

		return "articoli";
	}
	
	@RequestMapping(value = "/aggiungi", method = RequestMethod.GET)
	public String InsArticoli(Model model)
	{

		Clienti cliente = new Clienti();
		Utenti utente = new Utenti();

		model.addAttribute("Titolo", "Inserimento Nuovo Cliente");
		model.addAttribute("newCliente", cliente);
		model.addAttribute("newUtente", utente);
		 
		return "insCliente";
	}
	
	@RequestMapping(value = "/aggiungi", method = RequestMethod.POST)
	public String GestInsClienti(@ModelAttribute("newCliente") Clienti cliente, 
			@ModelAttribute("newUtente") Utenti utente,  BindingResult result,
			Model model, HttpServletRequest request)
	{

		 

		return "redirect:/articoli/infoart/" + utente.getUserId().trim();
	}
}