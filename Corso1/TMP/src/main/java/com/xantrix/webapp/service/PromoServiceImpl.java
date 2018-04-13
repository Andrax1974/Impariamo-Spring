package com.xantrix.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xantrix.webapp.entities.Promo;
import com.xantrix.webapp.repository.PromoRepository;

@Service
@Transactional
public class PromoServiceImpl implements PromoService
{
	@Autowired
	private PromoRepository promoRepository;
	

	@Override
	public List<Promo> SelTutti()
	{
		return promoRepository.findAll();
	}

}
