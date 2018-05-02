package com.xantrix.webapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xantrix.webapp.entities.Promo;
import com.xantrix.webapp.repository.PromoRepository;

@Service
@Transactional(readOnly = true)
public class PromoServiceImpl implements PromoService
{

	@Autowired
	private PromoRepository promoRepository;
	
	@Override
	public List<Promo> SelTutti()
	{
		return promoRepository.findAll();
	}

	@Override
	public Promo SelByIdPromo(String IdPromo)
	{
		 return promoRepository.findByIdPromo(IdPromo);
	}
	
	

	@Override
	public List<Promo> SelActivePromo()
	{
		Date today = new Date();
		return promoRepository.findActivePromo(today);
	}
	
	@Override
	public Promo SelByCodice(int Anno, String Codice)
	{
		return promoRepository.findByAnnoAndCodice(Anno, Codice);
	}
	
	@Override
	@Transactional
	public void InsPromo(Promo promo)
	{
		promoRepository.saveAndFlush(promo);
	}

	@Override
	@Transactional
	public void DelPromo(Promo promo)
	{
		promoRepository.delete(promo);
	}

}
