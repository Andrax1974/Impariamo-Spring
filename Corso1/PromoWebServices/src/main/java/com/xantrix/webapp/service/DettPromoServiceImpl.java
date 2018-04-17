package com.xantrix.webapp.service;

import java.util.List;

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
	public List<DettPromo> SelDettPromoByCodFid(String CodFid)
	{
		return dettPromoRepository.findDettPromoByCodFid(CodFid);
	}

	@Override
	public void InsDettPromo(DettPromo dettPromo)
	{
		dettPromoRepository.saveAndFlush(dettPromo);
	}

}
