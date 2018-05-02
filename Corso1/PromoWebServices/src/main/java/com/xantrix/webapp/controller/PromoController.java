package com.xantrix.webapp.controller;

 
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xantrix.webapp.entities.DettPromo;
import com.xantrix.webapp.entities.Promo;
import com.xantrix.webapp.exception.PromoException;
import com.xantrix.webapp.service.DettPromoService;
import com.xantrix.webapp.service.PromoService;

@RestController
//@CrossOrigin(origins = "http://localhost:8090")
public class PromoController
{
	private static final Logger logger = LoggerFactory.getLogger(PromoController.class);

	@Autowired
	private PromoService promoService;

	@Autowired
	private DettPromoService dettPromoService;

	@RequestMapping(value = "/promo", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Promo>> listAllPromo()
	{
		logger.info("****** Otteniamo tutte le promozioni *******");

		List<Promo> promo = promoService.SelTutti();

		if (promo.isEmpty())
		{
			return new ResponseEntity<List<Promo>>(HttpStatus.NO_CONTENT);
		}

		logger.info("Numero dei record: " + promo.size());
		
		return new ResponseEntity<List<Promo>>(promo, HttpStatus.OK);
	}

	@RequestMapping(value = "/promo/id/{idPromo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Promo> listPromoById(@PathVariable("idPromo") String IdPromo) throws PromoException
	{
		logger.info("****** Otteniamo la promozione con Id: " + IdPromo + "*******");

		Promo promo = promoService.SelByIdPromo(IdPromo);
		
		if (promo == null)
		{
			throw new PromoException("Promozione Assente");
			 //return new ResponseEntity<Promo>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<Promo>(promo, HttpStatus.OK);
	}
	
	//http://localhost:8091/promo/codice?anno=2018&codice=PP08
	@RequestMapping(value = "/promo/codice", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Promo> listPromoByCodice(@RequestParam("anno") String Anno,
			@RequestParam("codice") String Codice) throws PromoException
	{
		logger.info("****** Otteniamo la promozione con Codice: " + Codice + "*******");

		Promo promo = promoService.SelByCodice(Integer.valueOf(Anno), Codice);

		if (promo == null)
		{
			throw new PromoException("Promozione Assente");
			// return new ResponseEntity<Promo>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<Promo>(promo, HttpStatus.OK);
	}

	@RequestMapping(value = "/promo/active", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Promo>> listPromoActive() throws PromoException
	{
		logger.info("****** Otteniamo la Promozione Attive*******");

		List<Promo> promo = promoService.SelActivePromo();

		if (promo == null)
		{
			throw new PromoException("Promozione Assente");
			// return new ResponseEntity<Promo>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Promo>>(promo, HttpStatus.OK);
	}

	@RequestMapping(value = "/dettpromo/{idFidelity}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<DettPromo>> listDettPromoByCodFid(@PathVariable("idFidelity") String IdFidelity)
			throws PromoException
	{
		logger.info("****** Otteniamo le promozioni della Fidelity: " + IdFidelity + "*******");

		List<DettPromo> dettPromo = dettPromoService.SelDettPromoByCodFid(IdFidelity);

		logger.info("Numero Record: " + dettPromo.size());

		if (dettPromo.size() == 0)
		{
			return new ResponseEntity<List<DettPromo>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<DettPromo>>(dettPromo, HttpStatus.OK);
	}

	// ------------------- INSERT PROMO ------------------------------------
	@RequestMapping(value = "/promo/inserisci", method = RequestMethod.POST)
	public ResponseEntity<Promo> createPromo(@RequestBody Promo promo, UriComponentsBuilder ucBuilder)
	{
		logger.info("Creiamo una Promo con id " + promo.getIdPromo());

		promoService.InsPromo(promo);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/promo/id/{idPromo}").buildAndExpand(promo.getIdPromo()).toUri());

		return new ResponseEntity<Promo>(headers, HttpStatus.CREATED);
	}

	// ------------------- DELETE PROMO ----------------------------------
	@RequestMapping(value = "/promo/{idPromo}", method = RequestMethod.DELETE)
	public ResponseEntity deletePromo(@PathVariable("idPromo") String IdPromo) throws PromoException
	{
		logger.info("Eliminiamo la promo con id " + IdPromo);

		HttpHeaders headers = new HttpHeaders();
		ObjectMapper mapper = new ObjectMapper();

		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectNode responseNode = mapper.createObjectNode();

		Promo promo = promoService.SelByIdPromo(IdPromo);

		if (promo == null)
		{
			logger.info("ERRORE: Impossibile trovare la promo con id " + IdPromo);

			throw new PromoException("Promozione non trovata");
		}

		promoService.DelPromo(promo);

		responseNode.put("code", HttpStatus.OK.toString());
		responseNode.put("message", "Eliminazione Promozione" + IdPromo + " Eseguita Con Successo");

		return new ResponseEntity<>(responseNode, headers, HttpStatus.OK);
	}
	
	// ------------------- UPDATE DETTPROMO ------------------------------------
	@RequestMapping(value = "/dettpromo/modifica", method = RequestMethod.PUT)
	public ResponseEntity<DettPromo> updateDettPromo(@RequestParam("id") Long Id, @RequestParam("oggetto") String Oggetto)
	{
		logger.info("Modifichiamo il dettaglio promo " + Id);

		dettPromoService.UpdDettPromo(Id, Oggetto);
		
		return new ResponseEntity<DettPromo>(HttpStatus.CREATED);
	}
	
	// ------------------- DELETE DETTPROMO ------------------------------------
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/dettpromo/elimina/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteDettPromo(@PathVariable("id") Long Id)
	{
		logger.info("*******ELIMINAZIONE RIGA PROMO " + Id + "*******");
		
		HttpHeaders headers = new HttpHeaders();
		ObjectMapper mapper = new ObjectMapper();

		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectNode responseNode = mapper.createObjectNode();

		dettPromoService.DelRowPromo(Id);
		
		responseNode.put("code", HttpStatus.OK.toString());
		responseNode.put("message", "Eliminazione Eseguita Con Successo");
		
		return new ResponseEntity<>(responseNode, headers, HttpStatus.OK);
	}
	

}
