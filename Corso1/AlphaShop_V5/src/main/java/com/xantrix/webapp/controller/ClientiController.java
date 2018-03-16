package com.xantrix.webapp.controller;

 import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
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


import com.xantrix.webapp.entities.Cards;
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
	
	List<Clienti> MainRecordset;
	
	private Date date = new Date();
	
	private boolean IsSaved = false;
	private boolean IsClienti = true;
	
	private String OrderType = "DESC";
	private int OrderBy = 0;
	
	private int PageNum = 1;
	private int RecForPage = 10;
	
	private void GetAllClienti()
	{
		MainRecordset = clientiService.SelTutti();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String GetClienti(Model model)
	{
		if (MainRecordset == null)
			GetAllClienti();
		 
		 List<Clienti> recordset =  MainRecordset;
		
		 recordset = clientiService.SelTutti()
				 .stream()
				 .filter(u -> !u.getCodFidelity().equals("-1"))
				 .sorted(Comparator.comparing(Clienti::getCodFidelity))
				 .collect(Collectors.toList());
		 
		int NumRecords =  recordset.size();
	
		recordset = recordset
				.stream()
				.skip(0)
				.limit(RecForPage)
				.collect(Collectors.toList());

		
		 model.addAttribute("Titolo", "Ricerca Clienti"); 
		 model.addAttribute("Titolo2","Risultati Ricerca "); 
		 model.addAttribute("NumArt", NumRecords); 
		 model.addAttribute("clienti", recordset);
		 model.addAttribute("filter", "");
		 model.addAttribute("OrderType", OrderType);
		 model.addAttribute("OrderBy", OrderBy);
		 model.addAttribute("PageNum", PageNum);
		 model.addAttribute("RecPage", RecForPage);
		 model.addAttribute("IsClienti", IsClienti);

		return "clienti";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public String SearchArt(@RequestParam("filter") String pSearchTerm, ModelMap model) 
	{
		 List<Clienti> recordset;
		 
		 int NumItem = 0;
		 
		 recordset = clientiService.SelByNominativo(pSearchTerm)
				 .stream()
				 .filter(u -> !u.getCodFidelity().equals("-1"))
				 .filter(u -> !u.getCognome().equals(null))
				 .sorted(Comparator.comparing(Clienti::getCodFidelity))
				 .collect(Collectors.toList());
		 
		 NumItem = recordset.size();
		 
		 recordset = recordset.stream()
				 .skip(0)
				 .limit(RecForPage)
				 .collect(Collectors.toList());

		
		 model.addAttribute("NumArt", NumItem); 
		 model.addAttribute("Titolo", "Ricerca Clienti"); 
		 model.addAttribute("Titolo2","Risultati Ricerca "); 
		 model.addAttribute("clienti", recordset);
		 model.addAttribute("filter", pSearchTerm);
		 model.addAttribute("OrderType", OrderType);
		 model.addAttribute("OrderBy", OrderBy);
		 model.addAttribute("PageNum", PageNum);
		 model.addAttribute("RecPage", RecForPage);
		 model.addAttribute("IsClienti", IsClienti);

		return "clienti";
	}
	
	// http://localhost:8080/alphashop/clienti/cerca/parametri;filtro=Luisa,Nominativo;orderby=0,desc;paging=0,10
	@RequestMapping(value = "/cerca/{parametri}", method = RequestMethod.GET)
	public String GetClientiByFilterMatrix(@MatrixVariable(pathVar = "parametri") Map<String, List<String>> parametri, 
			Model model)
	{
			int ItemCount = 0;
			int SkipValue = 0;
			
			String Filter = "";
			String TypeFilter = "";
			
			boolean ChangeOrder = false;
			
			List<Clienti> recordset;
			
			List<String> ParamFiltro = parametri.get("filtro");
			if (ParamFiltro != null)
			{
				Filter = ParamFiltro.get(0);
				TypeFilter = ParamFiltro.get(1);
			}
			
			List<String> ParamOrderBy = parametri.get("orderby");
			if (ParamOrderBy != null)
			{
				try
				{
					OrderBy = Integer.parseInt(ParamOrderBy.get(0));
					OrderType = ParamOrderBy.get(1);
					ChangeOrder = (ParamOrderBy.get(2).equals("1")) ? true : false;
				}
				catch (NumberFormatException ex)
				{
					OrderBy = 0;
				}
			}

			List<String> ParamPaging = parametri.get("paging");
			if (ParamPaging != null)
			{
				try
				{
					PageNum = Integer.parseInt(ParamPaging.get(0));
					RecForPage = Integer.parseInt(ParamPaging.get(1));
					int DiffPage = Integer.parseInt(ParamPaging.get(2));
					
					if (PageNum >= 1)
						PageNum += DiffPage;
					
				}
				catch (NumberFormatException ex)
				{
					PageNum = 1;
					RecForPage = 10;
				}
			}

			if (Filter.length() > 0)
				recordset = clientiService.SelByNominativo(Filter);
			else
			{
				if (MainRecordset == null)
					GetAllClienti();
				
				recordset = MainRecordset;
			}
			
			if (recordset != null)
			{	
				recordset =  recordset.stream()
						.filter(u -> !u.getCodFidelity().equals("-1"))
						.filter(u -> !u.getCognome().equals(null))
						.collect(Collectors.toList());		
				
				ItemCount = recordset.size();
				
				SkipValue = GetSkipValue(PageNum, ItemCount, RecForPage);
				
				recordset = GestOrderRecordset(recordset, OrderBy, ChangeOrder)
						.stream()
						.skip(SkipValue)
						.limit(RecForPage)
						.collect(Collectors.toList());		 
			}

			model.addAttribute("NumArt", ItemCount); 
			model.addAttribute("Titolo", "Ricerca Clienti"); 
			model.addAttribute("Titolo2","Risultati Ricerca "); 
			model.addAttribute("clienti", recordset);
			model.addAttribute("filter", Filter);
			model.addAttribute("OrderType", OrderType);
			model.addAttribute("OrderBy", OrderBy);
			model.addAttribute("PageNum", PageNum);
			model.addAttribute("RecPage", RecForPage);
			model.addAttribute("IsClienti", IsClienti);
			
			return "clienti";
	}
	
	private int GetSkipValue(int PageNum, int ItemCount, int RecForPage)
	{
		int retVal = 0;
		
		if (ItemCount > RecForPage)
		{
			int NumTotPage = Math.round(ItemCount / RecForPage);
			
			if (PageNum <= NumTotPage)
				retVal = (PageNum - 1) * RecForPage;
		}
			
		return retVal;
	}

	private List<Clienti> GestOrderRecordset(List<Clienti> recordset, int OrderBy, boolean ChangeOrder)
	{
		if (ChangeOrder)
			OrderType = (OrderType.toUpperCase().equals("ASC")) ? "DESC" : "ASC";
		
		switch (OrderBy) 
		{
	        case 0:  
		        if (OrderType.toUpperCase().equals("ASC"))
		        {
		        	recordset = recordset.stream()
							 .sorted(Comparator.comparing(Clienti::getCodFidelity))
							 .collect(Collectors.toList()); 
		        }
		        else
		        {
		        	recordset = recordset.stream()
							.sorted(Comparator.comparing(Clienti::getCodFidelity).reversed()) 
							.collect(Collectors.toList());
		        }
		    break;
	        case 1:
	        	 if (OrderType.toUpperCase().equals("ASC"))
			        {
			        	recordset = recordset.stream()
								 .sorted(Comparator.comparing(Clienti::getCognome))
								 .collect(Collectors.toList()); 
			        }
			        else
			        {
			        	recordset = recordset.stream()
								.sorted(Comparator.comparing(Clienti::getCognome).reversed()) 
								.collect(Collectors.toList());
			        }
	        break;
	        case 3:
	        	 
	        	 Comparator<Clienti> byBollini =  (e1, e2) -> (Integer.compare(getBollini(e1.getCard()), getBollini(e2.getCard())));
	        	 
	        	 if (OrderType.toUpperCase().equals("ASC"))
			        {
	        		 	
	        			
			        	recordset =  recordset.stream()
			        			.sorted(byBollini).
								collect(Collectors.toList());
			        }
			        else
			        {
			        	 		
			        	recordset =  recordset.stream()
			        			.sorted(byBollini.reversed()).
								collect(Collectors.toList());
			        }
	        break;
		}
		
		return recordset;
	}
	
	private int getBollini(Cards card)
	{
		return (card == null) ? 0 : card.getBollini();
		
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
