package com.xantrix.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xantrix.webapp.entities.Promo;
import com.xantrix.webapp.service.PromoService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PromoController
{
	private static final Logger logger = LoggerFactory.getLogger(PromoController.class);
	
	@Autowired
	private PromoService promoService;
	
	@RequestMapping(value = "/promo", method = RequestMethod.GET)
	public ResponseEntity<List<Promo>> listAllPromo()
	{
		logger.info("****** Otteniamo tutte le promozioni *******");
		
		List<Promo> promo = promoService.SelTutti();

		if (promo.isEmpty())
		{
			return new ResponseEntity<List<Promo>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Promo>>(promo, HttpStatus.OK);
	}

}
