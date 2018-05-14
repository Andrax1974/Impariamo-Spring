package com.xantrix.webapp.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.xantrix.webapp.config.security.SpringSecurityUserContext;
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
	private static final Logger logger = LogManager.getLogger("Clienti");

	@Autowired
	private ClientiService clientiService;

	@Autowired
	private UtentiService utentiService;

	@Autowired
	private ProfiliService profiliService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private SpringSecurityUserContext userContext;
	
	Clienti LoggedUser = null;
	List<Clienti> MainRecordset;

	private Date date = new Date();

	private boolean IsSaved = false;
	private boolean IsClienti = true;

	private String OrderType = "DESC";
	private int OrderBy = 0;
	
	List<PagingData> Pages = new ArrayList<PagingData>();
	private int PageNum = 1;
	private int RecForPage = 10;

	private void GetAllClienti()
	{
		userContext = new SpringSecurityUserContext();
		MainRecordset = clientiService.SelTutti();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String GetClienti(Model model)
	{
		logger.info("Otteniamo tutti i clienti");
		
		GetAllClienti();
		
		logger.info("Utente Connesso: " + userContext.getCurrentClient());

		List<Clienti> recordset = MainRecordset
					.stream()
					.filter(u -> !u.getCodFidelity().equals("-1"))
					.sorted(Comparator.comparing(Clienti::getCodFidelity).reversed())
					.collect(Collectors.toList());

		long NumRecords = recordset.size();

		recordset = recordset.stream()
				.skip(0)
				.limit(RecForPage)
				.collect(Collectors.toList());
		
		logger.info("Numero di record per pagina: " + RecForPage);
		
		setPages(PageNum);

		model.addAttribute("Titolo", "Ricerca Clienti");
		model.addAttribute("Titolo2", "Risultati Ricerca ");
		model.addAttribute("NumRecords", NumRecords);
		model.addAttribute("clienti", recordset);
		model.addAttribute("filter", "");
		model.addAttribute("OrderType", OrderType);
		model.addAttribute("OrderBy", OrderBy);
		model.addAttribute("PageNum", PageNum);
		model.addAttribute("RecPage", RecForPage);
		model.addAttribute("Pages", Pages);
		model.addAttribute("IsClienti", IsClienti);
		model.addAttribute("User",  userContext.getCurrentClient());

		return "clienti";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String SearchItem(@RequestParam("filter") String pSearchTerm, ModelMap model)
	{
		List<Clienti> recordset;
		
		long BolliniByFilter = 0;
		long BolliniTot = 0;

		recordset = clientiService.SelByNominativo(pSearchTerm)
				.stream()
				.filter(u -> !u.getCodFidelity().equals("-1"))
				.filter(u -> u.getCard() != null)
				.sorted(Comparator.comparing(Clienti::getCodFidelity))
				.collect(Collectors.toList());

		LongSummaryStatistics BolliniStatistics = recordset
				.stream()
				.collect(Collectors.summarizingLong(p -> p.getCard().getBollini()));
		
		long NumRecords = BolliniStatistics.getCount();
		
		BolliniByFilter = BolliniStatistics.getSum();
		BolliniTot = clientiService.QtaTotBollini();

		recordset = recordset.stream()
				.skip(0)
				.limit(RecForPage)
				.collect(Collectors.toList());
		
		setPages(PageNum);
		 
		model.addAttribute("Titolo", "Ricerca Clienti");
		model.addAttribute("Titolo2", "Risultati Ricerca ");
		model.addAttribute("NumRecords", NumRecords);
		model.addAttribute("clienti", recordset);
		model.addAttribute("filter", pSearchTerm);
		model.addAttribute("OrderType", OrderType);
		model.addAttribute("OrderBy", OrderBy);
		model.addAttribute("PageNum", PageNum);
		model.addAttribute("RecPage", RecForPage);
		model.addAttribute("Pages", Pages);
		model.addAttribute("IsClienti", IsClienti);
		model.addAttribute("BolFil", BolliniByFilter);
		model.addAttribute("BolTot", BolliniTot);
		model.addAttribute("User",  userContext.getCurrentClient());

		return "clienti";
	}

	// http://localhost:8080/alphashop/clienti/cerca/parametri;filtro=Luisa,Nominativo;orderby=0,desc;paging=0,10
	@RequestMapping(value = "/cerca/{parametri}", method = RequestMethod.GET)
	public String GetClientiByFilterMatrix(@MatrixVariable(pathVar = "parametri") Map<String, List<String>> parametri,
			Model model)
	{
		
		long NumRecords = 0;
		long SkipValue = 0;
		long BolliniByFilter = 0;
		long BolliniTot = 0;

		String Filter = "";
		String TypeFilter = "";

		boolean ChangeOrder = false;

		List<Clienti> recordset;

		//PARAMETRI FILTRO
		List<String> ParamFiltro = parametri.get("filtro");
		if (ParamFiltro != null)
		{
			Filter = ParamFiltro.get(0);
			TypeFilter = ParamFiltro.get(1);
		}

		//PARAMETRI ORDINAMENTO
		List<String> ParamOrderBy = parametri.get("orderby");
		if (ParamOrderBy != null)
		{
			try
			{
				OrderBy = Integer.parseInt(ParamOrderBy.get(0));
				OrderType = ParamOrderBy.get(1);
				ChangeOrder = (ParamOrderBy.get(2).equals("1")) ? true : false;
			} catch (NumberFormatException ex)
			{
				OrderBy = 0;
			}
		}

		//PARAMETRI PAGING
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
				else
					PageNum = 1;

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
			recordset = recordset
					.stream()
					.filter(u -> !u.getCodFidelity().equals("-1"))
					.filter(u -> u.getCard() != null)
					.collect(Collectors.toList());

			LongSummaryStatistics BolliniStatistics = recordset
					.stream()
					.collect(Collectors.summarizingLong(p -> p.getCard().getBollini()));
			
			NumRecords = BolliniStatistics.getCount();
			
			BolliniByFilter = BolliniStatistics.getSum();
			BolliniTot = clientiService.QtaTotBollini();

			SkipValue = GetSkipValue(PageNum, NumRecords, RecForPage);

			recordset = GestOrderRecordset(recordset, OrderBy, ChangeOrder)
					.stream()
					.skip(SkipValue).limit(RecForPage)
					.collect(Collectors.toList());
		}

		setPages(PageNum);
		
		model.addAttribute("Titolo", "Ricerca Clienti");
		model.addAttribute("Titolo2", "Risultati Ricerca ");
		model.addAttribute("NumRecords", NumRecords);
		model.addAttribute("clienti", recordset);
		model.addAttribute("filter", Filter);
		model.addAttribute("OrderType", OrderType);
		model.addAttribute("OrderBy", OrderBy);
		model.addAttribute("PageNum", PageNum);
		model.addAttribute("RecPage", RecForPage);
		model.addAttribute("Pages", Pages);
		model.addAttribute("IsClienti", IsClienti);
		model.addAttribute("BolTot", BolliniTot);
		model.addAttribute("BolFil", BolliniByFilter);
		model.addAttribute("User",  userContext.getCurrentClient());
		
		return "clienti";
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
				recordset = recordset.stream().sorted(Comparator.comparing(Clienti::getCodFidelity))
						.collect(Collectors.toList());
			} else
			{
				recordset = recordset.stream().sorted(Comparator.comparing(Clienti::getCodFidelity).reversed())
						.collect(Collectors.toList());
			}
			break;
		case 1:
			if (OrderType.toUpperCase().equals("ASC"))
			{
				recordset = recordset.stream().sorted(Comparator.comparing(Clienti::getCognome))
						.collect(Collectors.toList());
			} else
			{
				recordset = recordset.stream().sorted(Comparator.comparing(Clienti::getCognome).reversed())
						.collect(Collectors.toList());
			}
			break;
		case 3:

			Comparator<Clienti> byBollini = (e1, e2) -> (Integer.compare(getBollini(e1.getCard()), getBollini(e2.getCard())));

			if (OrderType.toUpperCase().equals("ASC"))
			{

				recordset = recordset.stream().sorted(byBollini).collect(Collectors.toList());
			} else
			{

				recordset = recordset.stream().sorted(byBollini.reversed()).collect(Collectors.toList());
			}
			break;
		}

		return recordset;
	}

	private int getBollini(Cards card)
	{
		return (card == null) ? 0 : card.getBollini();
	}

	// http://localhost:8080/alphashop/clienti/cerca/comune?filter=alghero
	@RequestMapping(value = "/cerca/comune", method = RequestMethod.GET)
	public String GetClientiByComune(@RequestParam("filter") String Comune, Model model)
	{
		MainRecordset = clientiService.SelByComune(Comune);

		MainRecordset = MainRecordset
				.stream()
				.filter(u -> !u.getCodFidelity().equals("-1"))
				.filter(u -> u.getCard() != null)
				.sorted(Comparator.comparing(Clienti::getCodFidelity))
				.collect(Collectors.toList());

		LongSummaryStatistics BolliniStatistics = MainRecordset
				.stream()
				.collect(Collectors.summarizingLong(p -> p.getCard().getBollini()));
		
		long BolliniByFilter = BolliniStatistics.getSum();
		long NumRecords = BolliniStatistics.getCount();
		
		long BolliniTot = clientiService.QtaTotBollini();
		
		MainRecordset = MainRecordset.stream()
				.skip(0).limit(RecForPage)
				.collect(Collectors.toList());
		
		setPages(PageNum);
		
		model.addAttribute("Titolo", "Ricerca Clienti per Comune");
		model.addAttribute("Titolo2", "Risultati Ricerca ");
		model.addAttribute("NumRecords", NumRecords);
		model.addAttribute("clienti", MainRecordset);
		model.addAttribute("filter", "");
		model.addAttribute("OrderType", OrderType);
		model.addAttribute("OrderBy", OrderBy);
		model.addAttribute("PageNum", PageNum);
		model.addAttribute("RecPage", RecForPage);
		model.addAttribute("Pages", Pages);
		model.addAttribute("IsClienti", IsClienti);
		model.addAttribute("BolTot", BolliniTot);
		model.addAttribute("BolFil", BolliniByFilter);
		model.addAttribute("User",  userContext.getCurrentClient());

		return "clienti"; 

	}
	
	// http://localhost:8080/alphashop/clienti/cerca/bollini?filter=1000&type=>
	@RequestMapping(value = "/cerca/bollini", method = RequestMethod.GET)
	public String GetClientiByBollini(@RequestParam("filter") int Bollini, @RequestParam("type") String Tipo, Model model)
	{
		MainRecordset = clientiService.SelByBollini(Bollini, Tipo);
		
		List<Clienti> recordset = MainRecordset;

		recordset = recordset
				.stream()
				.filter(u -> !u.getCodFidelity().equals("-1"))
				.filter(u -> u.getCard() != null)
				//.sorted(Comparator.comparing(Clienti::getCodFidelity))
				.collect(Collectors.toList());

		LongSummaryStatistics BolliniStatistics = recordset
				.stream()
				.collect(Collectors.summarizingLong(p -> p.getCard().getBollini()));
		
		long BolliniByFilter = BolliniStatistics.getSum();
		long NumRecords = BolliniStatistics.getCount();
		
		long BolliniTot = clientiService.QtaTotBollini();

		recordset = recordset.stream()
				.skip(0).limit(RecForPage)
				.collect(Collectors.toList());
		
		setPages(PageNum);

		model.addAttribute("Titolo", "Ricerca Clienti per limite Bollini");
		model.addAttribute("Titolo2", "Risultati Ricerca ");
		model.addAttribute("NumRecords", NumRecords);
		model.addAttribute("clienti", recordset);
		model.addAttribute("filter", "");
		model.addAttribute("OrderType", OrderType);
		model.addAttribute("OrderBy", OrderBy);
		model.addAttribute("PageNum", PageNum);
		model.addAttribute("RecPage", RecForPage);
		model.addAttribute("Pages", Pages);
		model.addAttribute("IsClienti", IsClienti);
		model.addAttribute("BolTot", BolliniTot);
		model.addAttribute("BolFil", BolliniByFilter);
		model.addAttribute("User",  userContext.getCurrentClient());

		return "clienti"; 

	}

	@RequestMapping(value = "/aggiungi", method = RequestMethod.GET)
	public String InsCliente(Model model)
	{

		Clienti cliente = new Clienti();
		
		int LastCodFid = Integer.parseInt(clientiService.SelLastCodFid()); 
		
		cliente.setCodFidelity(Integer.toString(LastCodFid + 1));

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
	public String GestInsCliente(@Valid @ModelAttribute("Cliente") Clienti cliente, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request)
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
		model.addAttribute("saved", IsSaved ? true : false);

		IsSaved = false;

		return "insCliente";
	}

	@RequestMapping(value = "/modifica/{idCliente}", method = RequestMethod.POST)
	public String GestUpdClienti(
			@Valid @ModelAttribute("Cliente") Clienti cliente,
			BindingResult result,
			@ModelAttribute("Utente") Utenti utente, 
			@ModelAttribute("Profilo") Profili profilo,
			@PathVariable("idCliente") String IdCliente, 
			Model model, HttpServletRequest request)
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
			
			//CRITTIAMO LA PASSWORD
			String encodedPassword = passwordEncoder.encode(utente.getPwd());
			utente.setPwd(encodedPassword); 

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
		} catch (Exception ex)
		{
			logger.debug("ERRORE: " + ex.getMessage());
		}

		return "redirect:/clienti/";

	}

	// http://localhost:8080/alphashop/clienti/modifica/delprofilo/67100950/14
	@RequestMapping(value =
	{ "modifica/delprofilo/{idCliente}/{id}" }, method = RequestMethod.GET)
	public String DelProfilo(@PathVariable("idCliente") int IdCliente, @PathVariable("id") int Id)
	{
		Profili Profilo = profiliService.SelById(Id);

		profiliService.Elimina(Profilo);

		return "redirect:/clienti/modifica/" + IdCliente;
	}
	
	private void setPages(int Page)
	{
		int Min = 1;
		int ValMin = 1;
		int Max = 5;
		
		if (Pages != null)
			Pages.clear();
		
		int Group = (int) Math.ceil((double)Page / 5);
		
		Max = Group * 5;
		Min = (Max - 5 == 0) ? 1 : Max - 4;
		
		ValMin = Min;
		
		while (Min <= Max)
		{
			Pages.add(new PagingData(Min,false));
			
			Min++;
		}
		
		if (Page - ValMin > 0)
			Pages.get(Page - ValMin).setIsSelected(true);
		else
			Pages.get(0).setIsSelected(true);
	}

	private int GetSkipValue(int PageNum, long numRecords, int RecForPage)
	{
		int retVal = 0;

		if (numRecords > RecForPage)
		{
			int NumTotPage = Math.round(numRecords / RecForPage);

			if (PageNum <= NumTotPage)
				retVal = (PageNum - 1) * RecForPage;
		}

		return retVal;
	}
}




/*
// http://localhost:8080/alphashop/clienti/cerca/
@RequestMapping(value = "/cerca2/{filter}", method = RequestMethod.GET)
public String GetClientiByFilter(@PathVariable("filter") String Filter, Model model)
{
	Clienti cliente = clientiService.SelCliente(Filter);


	return "articoli";
}
*/