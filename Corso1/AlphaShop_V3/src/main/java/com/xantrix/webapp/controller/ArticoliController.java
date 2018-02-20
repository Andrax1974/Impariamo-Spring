package com.xantrix.webapp.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.text.DateFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.xantrix.webapp.domain.Articoli;
import com.xantrix.webapp.domain.FamAssort;
import com.xantrix.webapp.domain.Iva;
import com.xantrix.webapp.service.ArticoliService;
import com.xantrix.webapp.service.FamAssortService;
import com.xantrix.webapp.service.IvaService;

@Controller
@RequestMapping("/articoli")
public class ArticoliController
{
	@Autowired
	private ArticoliService articoliService;

	@Autowired
	private FamAssortService famAssortService;

	@Autowired
	private IvaService ivaService;

	private int NumArt = 0;
	private List<Articoli> recordset;

	// http://localhost:8080/alphashop/articoli/cerca/barilla
	@RequestMapping(value = "/cerca/{filter}", method = RequestMethod.GET)
	public String GetArticoliByFilter(@PathVariable("filter") String Filter, Model model)
	{
		recordset = articoliService.SelArticoliByFilter(Filter);

		if (recordset != null)
			NumArt = recordset.size();

		model.addAttribute("NumArt", NumArt);
		model.addAttribute("Titolo", "Ricerca Articoli");
		model.addAttribute("Titolo2", "Risultati Ricerca " + Filter);
		model.addAttribute("Articoli", recordset);

		return "articoli";
	}

	// http://localhost:8080/alphashop/articoli/cerca?filter=barilla&rep=1
	@RequestMapping(value = "/cerca", method = RequestMethod.GET)
	public String GetArticoliByFilter(@RequestParam("filter") String Filter, @RequestParam("rep") int IdRep,
			Model model)
	{

		List<Articoli> recordset = articoliService.SelArticoliByFilter(Filter).stream()
				.filter(u -> u.getIdFamAss() == IdRep).collect(Collectors.toList());

		if (recordset != null)
			NumArt = recordset.size();

		model.addAttribute("NumArt", NumArt);
		model.addAttribute("Titolo", "Ricerca Articoli");
		model.addAttribute("Titolo2", "Risultati Ricerca " + Filter);
		model.addAttribute("Articoli", recordset);

		return "articoli";
	}

	// http://localhost:8080/alphashop/articoli/cerca/barilla/parametri;reparti=1,10,15;orderby=codart,desc;paging=0,10
	@RequestMapping(value = "/cerca/{filter}/{parametri}", method = RequestMethod.GET)
	public String GetArticoliByFilterMatrix(@PathVariable("filter") String Filter,
			@MatrixVariable(pathVar = "parametri") Map<String, List<String>> parametri, Model model)
	{
		int NumArt = 0;
		String orderBy = "codart";
		String tipo = "desc";
		Long SkipValue = (long) 0;
		Long LimitValue = (long) 10;

		List<String> IdRep = parametri.get("reparti");
		List<String> OrderBy = parametri.get("orderby");
		List<String> Paging = parametri.get("paging");

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

		List<Articoli> recordset = articoliService.SelArticoliByFilter(Filter, orderBy, tipo);

		recordset = recordset.stream().filter(u -> IdRep.contains(Integer.toString(u.getIdFamAss())))
				.filter(u -> u.getQtaMag() > 0).filter(u -> u.getPrezzo() > 0).collect(Collectors.toList());

		if (recordset != null)
			NumArt = recordset.size();

		recordset = recordset.stream().skip(SkipValue).limit(LimitValue).collect(Collectors.toList());

		/*
		 * if (orderBy.equals("codart") && tipo.equals("asc")) recordset =
		 * recordset.stream().sorted(Comparator.comparing(Articoli::getCodArt))
		 * .collect(Collectors.toList()); else if (orderBy.equals("codart") &&
		 * tipo.equals("desc")) recordset =
		 * recordset.stream().sorted(Comparator.comparing(Articoli::getCodArt).
		 * reversed()) .collect(Collectors.toList());
		 */

		model.addAttribute("Articoli", recordset);
		model.addAttribute("NumArt", NumArt);
		model.addAttribute("Titolo", "Ricerca Articoli");

		return "articoli";
	}

	// http://localhost:8080/AlphaShop/articoli/cerca/barilla/creati?daData=2010-10-31&aData=2015-10-31
	@RequestMapping(value = "/cerca/{filter}/creati", method = RequestMethod.GET)
	public String GetArticoliByFilterDate(@PathVariable("filter") String Filter,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("daData") Date startDate,
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("aData") Date endDate, Model model)
	{

		List<Articoli> recordset = articoliService.SelArticoliByFilter(Filter).stream()
				.filter(u -> u.getDataCreaz().after(startDate)).filter(U -> U.getDataCreaz().before(endDate))
				.collect(Collectors.toList());

		if (recordset != null)
			NumArt = recordset.size();

		model.addAttribute("NumArt", NumArt);
		model.addAttribute("Titolo", "Ricerca Articoli");
		model.addAttribute("Titolo2", "Risultati Ricerca " + Filter);
		model.addAttribute("Articoli", recordset);

		return "articoli";
	}

	// http://localhost:8080/alphashop/articoli/infoart/000087101
	@RequestMapping(value = "/infoart/{codart}", method = RequestMethod.GET)
	public String GetDettArticolo(@PathVariable("codart") String CodArt, Model model)
	{
		Articoli articolo = null;
		recordset = articoliService.SelArticoliByFilter(CodArt);

		if (recordset != null)
			articolo = recordset.get(0);

		model.addAttribute("Titolo", "Dettaglio Articolo");
		model.addAttribute("Titolo2", "Dati Articolo " + CodArt);
		model.addAttribute("articolo", articolo);

		return "infoArticolo";
	}

	// http://localhost:8080/alphashop/articoli/lastart
	@RequestMapping(value = "/lastart", method = RequestMethod.GET)
	public String GetArticoliByFilter(@RequestParam(value = "numart", defaultValue = "1", required = false) long NumArt,
			Model model)
	{

		List<Articoli> recordset = articoliService.SelArticoliByFilter("", "DATACREAZIONE", "DESC");

		recordset = recordset.stream().limit(NumArt).collect(Collectors.toList());

		if (recordset != null)
			NumArt = recordset.size();

		model.addAttribute("NumArt", NumArt);
		model.addAttribute("Titolo", "Ricerca Articoli");
		model.addAttribute("Titolo2", "Ultimi " + NumArt + " Articoli Creati");
		model.addAttribute("Articoli", recordset);

		return "articoli";
	}

	@RequestMapping(value = "/aggiungi", method = RequestMethod.GET)
	public String InsArticoli(Model model)
	{

		List<FamAssort> famAssort = famAssortService.SelFamAssort();
		List<Iva> iva = ivaService.SelIva();

		Articoli articolo = new Articoli();

		model.addAttribute("Titolo", "Inserimento Nuovo Articolo");
		model.addAttribute("newArticolo", articolo);
		model.addAttribute("famAssort", famAssort);
		model.addAttribute("Iva", iva);

		return "insArticolo";
	}

	@RequestMapping(value = "/aggiungi", method = RequestMethod.POST)
	public String GestInsArticoli(@ModelAttribute("newArticolo") Articoli articolo, BindingResult result)
	{
		if (result.getSuppressedFields().length > 0)
			throw new RuntimeException("ERRORE: Tentativo di eseguire il binding dei seguenti campi NON consentiti: "
					+ StringUtils.arrayToCommaDelimitedString(result.getSuppressedFields()));
		else
		{
			articoliService.InsArticolo(articolo);

		}

		return "redirect:/articoli/lastart";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder)
	{
		binder.setAllowedFields("codArt", "descrizione", "um", "pzCart", "pesoNetto", "idIva", "idStatoArt","idFamAss","dataCreaz");

		binder.setDisallowedFields("prezzo");

	}

}
