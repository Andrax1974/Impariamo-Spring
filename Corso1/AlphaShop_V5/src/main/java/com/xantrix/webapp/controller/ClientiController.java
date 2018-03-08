package com.xantrix.webapp.controller;

 import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xantrix.webapp.entities.Clienti;
import com.xantrix.webapp.entities.Profili;
import com.xantrix.webapp.entities.Utenti;
import com.xantrix.webapp.service.ClientiService;
import com.xantrix.webapp.service.ProfiliService;
import com.xantrix.webapp.service.UtentiService;
 

@Controller
@RequestMapping("/clienti")
public class ClientiController
{
	@Autowired
	private ClientiService clientiService;
	
	@Autowired
	private UtentiService utentiService;
			
	@Autowired
	private ProfiliService profiliService;

	private List<Clienti> recordset;
	
	private Date date = new Date();

	// http://localhost:8080/alphashop/clienti/cerca/
	@RequestMapping(value = "/cerca/{filter}", method = RequestMethod.GET)
	public String GetClientiByFilter(@PathVariable("filter") String Filter, Model model)
	{
		Clienti cliente = clientiService.SelCliente(Filter);

		/*
		 * model.addAttribute("NumArt", NumArt); model.addAttribute("Titolo",
		 * "Ricerca Articoli"); model.addAttribute("Titolo2",
		 * "Risultati Ricerca " + Filter); model.addAttribute("Articoli",
		 * recordset);
		 */

		return "articoli";
	}
	
	@RequestMapping(value = "/cerca", method = RequestMethod.GET)
	public String GetClienti(Model model)
	{
		 recordset = clientiService.SelTutti();
		
		 model.addAttribute("NumArt", recordset.size()); 
		 model.addAttribute("Titolo", "Ricerca Clienti"); 
		 model.addAttribute("Titolo2","Risultati Ricerca "); 
		 model.addAttribute("clienti", recordset);
		 

		return "clienti";
	}

	@RequestMapping(value = "/aggiungi", method = RequestMethod.GET)
	public String InsArticoli(Model model)
	{

		Clienti cliente = new Clienti();
		Utenti utente = new Utenti();
		 
		model.addAttribute("Titolo", "Inserimento Nuovo Cliente");
		model.addAttribute("Cliente", cliente);
		model.addAttribute("Utente", utente);
		model.addAttribute("edit", false);
		
		return "insCliente";
	}

	@RequestMapping(value = "/aggiungi", method = RequestMethod.POST)
	public String GestInsClienti(@ModelAttribute("Cliente") Clienti cliente, 
			BindingResult result, Model model,
			HttpServletRequest request)
	{
		
		cliente.setDataCreaz(date);
		clientiService.Salva(cliente);
		
		return "redirect:/clienti/modifica/" + cliente.getCodFidelity().trim();
	}

	@RequestMapping(value = "/modifica/{idCliente}", method = RequestMethod.GET)
	public String UpdClienti(@PathVariable("idCliente") String IdCliente, Model model)
	{
		Clienti cliente = clientiService.SelCliente(IdCliente);
		
		Utenti utente = utentiService.SelByIdFidelity(IdCliente);
		utente.setCodFidelity(IdCliente);
		utente.setPwd("");
		
		Profili profilo = new Profili();
		
		model.addAttribute("Titolo", "Modifica Cliente");
		model.addAttribute("Cliente", cliente);
		model.addAttribute("Utente", utente);
		model.addAttribute("Profilo", profilo);
		model.addAttribute("edit", true);
		 
		return "insCliente";
	}

	@RequestMapping(value = "/modifica/{idCliente}", method = RequestMethod.POST)
	public String GestUpdClienti(
			@ModelAttribute("Cliente") Clienti cliente, 
			@ModelAttribute("Utente") Utenti utente,
			@ModelAttribute("Profilo") Profili profilo,
			@PathVariable("idCliente") String IdCliente,
			BindingResult result, Model model,
			HttpServletRequest request)
	{
		Set<Profili> profili = new HashSet<>();
		
		if (cliente.getNome() != null)
		{
			cliente.setDataCreaz(date);
			clientiService.Aggiorna(cliente);
		}
		
		if (utente.getUserId() != null)
		{
			Utenti test = utentiService.SelByIdFidelity(utente.getCodFidelity());
			
			profili.add(new Profili("USER", utente));
			
			utente.setProfili(profili);
			
			if (test.getUserId() != null)
				utentiService.Aggiorna(utente);
			else
				utentiService.Salva(utente);
		}
		
		if (profilo.getTipo() != null)
		{
			
			Utenti Utente = new Utenti(IdCliente);
			String Tipo = profilo.getTipo();
			
			Profili newProfilo = new Profili(Tipo, Utente);
			
			profiliService.Salva(newProfilo);
		}
		
		
		return "redirect:/clienti/modifica/" + IdCliente;
	}
	
	//http://localhost:8080/alphashop/clienti/modifica/delprofilo/67100950/14
	@RequestMapping(value ={ "modifica/delprofilo/{idCliente}/{id}" }, method = RequestMethod.GET)
	public String DelProfilo(@PathVariable("idCliente") int IdCliente, @PathVariable("id") int Id)
	{
		Profili Profilo = profiliService.SelById(Id);
		
		profiliService.Elimina(Profilo);

		return "redirect:/clienti/modifica/" + IdCliente;
	}
}
