package com.xantrix.webapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xantrix.webapp.entities.DettPromo;
import com.xantrix.webapp.repository.DettPromoRepository;
 
@Service
@Transactional(readOnly = true)
public class DettPromoServiceImpl implements DettPromoService
{

	@Autowired
	private DettPromoRepository dettPromoRepository;
	
	@Override
	public Optional<DettPromo> SelDettPromo(long Id)
	{
		return dettPromoRepository.findById(Id);
	}
	
	@Override
	public List<DettPromo> SelDettPromoByCodFid(String CodFid)
	{
		return dettPromoRepository.findDettPromoByCodFid(CodFid);
	}

	@Override
	@Transactional
	public void InsDettPromo(DettPromo dettPromo)
	{
		dettPromoRepository.saveAndFlush(dettPromo);
	}

	@Override
	@Transactional
	public void UpdDettPromo(Long Id, String Oggetto)
	{
		 dettPromoRepository.UpdOggettoPromo(Oggetto, Id);
	}

	@Override
	@Transactional
	public void DelRowPromo(Long Id)
	{
		dettPromoRepository.DelRowPromo(Id);
	}

}
