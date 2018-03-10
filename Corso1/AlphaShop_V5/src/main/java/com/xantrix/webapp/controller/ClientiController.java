package com.xantrix.webapp.controller;

 import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xantrix.webapp.domain.Articoli;
import com.xantrix.webapp.domain.Iva;
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
	private final Logger logger = LogManager.getLogger("Clienti");
	
	@Autowired
	private ClientiService clientiService;
	
	@Autowired
	private UtentiService utentiService;
			
	@Autowired
	private ProfiliService profiliService;

	private List<Clienti> recordset;
	
	private Date date = new Date();
	
	private boolean IsSaved = false;
	private boolean IsClienti = true;
	
	@RequestMapping(method = RequestMethod.GET)
	public String GetClienti(Model model)
	{
		
		 recordset = clientiService.SelTutti()
				 .stream()
				 .filter(u -> !u.getCodFidelity().equals("-1"))
				 .skip(0).limit(10)
				 .sorted(Comparator.comparing(Clienti::getCodFidelity))
				 .collect(Collectors.toList());

		
		 model.addAttribute("NumArt", recordset.size()); 
		 model.addAttribute("Titolo", "Ricerca Clienti"); 
		 model.addAttribute("Titolo2","Risultati Ricerca "); 
		 model.addAttribute("clienti", recordset);
		 model.addAttribute("IsClienti", IsClienti);

		return "clienti";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String SearchArt(@RequestParam("filter") String pSearchTerm, ModelMap model) 
	{
		 recordset = clientiService.SelByNominativo(pSearchTerm)
				 .stream()
				 .filter(u -> !u.getCodFidelity().equals("-1"))
				 .skip(0).limit(10)
				 .sorted(Comparator.comparing(Clienti::getCodFidelity))
				 .collect(Collectors.toList());

		
		 model.addAttribute("NumArt", recordset.size()); 
		 model.addAttribute("Titolo", "Ricerca Clienti"); 
		 model.addAttribute("Titolo2","Risultati Ricerca "); 
		 model.addAttribute("clienti", recordset);
		 model.addAttribute("filter", pSearchTerm);
		 model.addAttribute("IsClienti", IsClienti);

		return "clienti";
	}
	
	// http://localhost:8080/alphashop/clienti/cerca/parametri;filtro=Luisa,Nominativo;orderby=fidelity,desc;paging=0,10
	@RequestMapping(value = "/cerca/{parametri}", method = RequestMethod.GET)
	public String GetClientiByFilterMatrix(@MatrixVariable(pathVar = "parametri") Map<String, List<String>> parametri, 
			Model model)
	{
			int ItemNum = 0;
			
			String filter = "";
			String type = "";
			
			String orderBy = "fidelity";
			String tipo = "desc";
			
			Long SkipValue = (long) 0;
			Long LimitValue = (long) 10;

			List<Clienti> recordset;
			
			List<String> Filtro = parametri.get("filtro");
			List<String> OrderBy = parametri.get("orderby");
			List<String> Paging = parametri.get("paging");

			if (Filtro != null)
			{
				filter = Filtro.get(0);
				type = Filtro.get(1);
			}
			
			if (OrderBy != null)
			{
				orderBy = OrderBy.get(0);
				tipo = OrderBy.get(1);
			}

			if (Paging != null)
			{
				SkipValue = Long.parseLong(Paging.get(0));
				LimitValue = Long.parseLong(Paging.get(1));
			}

			
			if (filter.length() > 0)
				recordset = clientiService.SelByNominativo(filter);
			else
				recordset = clientiService.SelTutti();
			
			ItemNum = recordset.size();
			
			if (recordset != null)
			{
				recordset = recordset.stream()
						.filter(u -> !u.getCodFidelity().equals("-1"))
						.skip(SkipValue)
						.limit(LimitValue)
						.collect(Collectors.toList());
	
				
				if (orderBy.equals("fidelity") && tipo.equals("asc"))
				{
					recordset = recordset.stream()
							 .sorted(Comparator.comparing(Clienti::getCodFidelity))
							 .collect(Collectors.toList()); 
				}
				else if (orderBy.equals("fidelity") && tipo.equals("desc")) 
				{
					recordset = recordset.stream()
							.sorted(Comparator.comparing(Clienti::getCodFidelity).reversed()) 
							.collect(Collectors.toList());
				}
				else if (orderBy.equals("nominativo") && tipo.equals("asc")) 
				{
					recordset = recordset.stream()
							.sorted(Comparator.comparing(Clienti::getCognome)) 
							.collect(Collectors.toList());
				}
				else if (orderBy.equals("nominativo") && tipo.equals("desc")) 
				{
					recordset = recordset.stream()
							.sorted(Comparator.comparing(Clienti::getCognome).reversed()) 
							.collect(Collectors.toList());
				}
				 
			}

			model.addAttribute("NumArt", ItemNum); 
			model.addAttribute("Titolo", "Ricerca Clienti"); 
			model.addAttribute("Titolo2","Risultati Ricerca "); 
			model.addAttribute("clienti", recordset);

			return "clienti";
		}

	// http://localhost:8080/alphashop/clienti/cerca/
	@RequestMapping(value = "/cerca2/{filter}", method = RequestMethod.GET)
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
	
	
	@RequestMapping(value = "/elimina/{idCliente}", method = RequestMethod.GET)
	public String DelClienti(@PathVariable("idCliente") String IdCliente, Model model)
	{
		try
		{
			 logger.info("Eliminazione Cliente con Codice: " + IdCliente);
			 
			 if (!IdCliente.equals("-1"))
			 {
				 Clienti cliente = clientiService.SelCliente(IdCliente);
				 clientiService.Elimina(cliente);
			 }
		}
		catch (Exception ex)
		{
			 logger.debug("ERRORE: " + ex.getMessage());
		}
		 
		 return "redirect:/clienti/modifica/" + IdCliente;
	 
	}

	@RequestMapping(value = "/aggiungi", method = RequestMethod.GET)
	public String InsArticoli(Model model)
	{

		Clienti cliente = new Clienti();
		
		model.addAttribute("Titolo", "Inserimento Nuovo Cliente");
		model.addAttribute("Cliente", cliente);
		model.addAttribute("Utente", getUtente());
		model.addAttribute("Profilo", getProfilo());
		model.addAttribute("edit", false);
		model.addAttribute("saved", false);
		
		return "insCliente";
	}
	
	@ModelAttribute("Utente")
	public Utenti getUtente()
	{
		return new Utenti();
	}
	
	@ModelAttribute("Profilo")
	public Profili getProfilo()
	{
		return new Profili();
	}
	
	@RequestMapping(value = "/aggiungi", method = RequestMethod.POST)
	public String GestInsClienti(@Valid @ModelAttribute("Cliente") Clienti cliente, 
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes,
			HttpServletRequest request)
	{
		if (result.hasErrors())
		{	
			return "insCliente";
		}
		
		cliente.setDataCreaz(date);
		clientiService.Salva(cliente);
		
		redirectAttributes.addFlashAttribute("saved", true);
		
		return "redirect:/clienti/modifica/" + cliente.getCodFidelity().trim();
	}

	@RequestMapping(value = "/modifica/{idCliente}", method = RequestMethod.GET)
	public String UpdClienti(@PathVariable("idCliente") String IdCliente, Model model)
	{
		Clienti cliente = clientiService.SelCliente(IdCliente);
		
		Utenti utente = utentiService.SelByIdFidelity(IdCliente);
		utente.setCodFidelity(IdCliente);
		utente.setPwd("");
		
		model.addAttribute("Titolo", "Modifica Cliente");
		model.addAttribute("Cliente", cliente);
		model.addAttribute("Utente", utente);
		model.addAttribute("Profilo", getProfilo());
		model.addAttribute("edit", true);
		model.addAttribute("saved", IsSaved ? true : false );
		
		IsSaved = false;
		
		return "insCliente";
	}

	@RequestMapping(value = "/modifica/{idCliente}", method = RequestMethod.POST)
	public String GestUpdClienti(
			@Valid @ModelAttribute("Cliente") Clienti cliente, 
			@ModelAttribute("Utente") Utenti utente,
			@ModelAttribute("Profilo") Profili profilo,
			@PathVariable("idCliente") String IdCliente,
			BindingResult result, Model model,
			HttpServletRequest request)
	{
		Set<Profili> profili = new HashSet<>();
		
		if (cliente.getNome() != null)
		{
			if (result.hasErrors())
			{
				return "insCliente";
			}
			
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
		
		IsSaved = true; 
		
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
