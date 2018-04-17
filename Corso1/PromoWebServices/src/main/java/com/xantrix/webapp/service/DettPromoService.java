package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.entities.DettPromo;
import com.xantrix.webapp.entities.Promo;

public interface DettPromoService
{
	List<DettPromo> SelDettPromoByCodFid(String CodFid);
	
	void InsDettPromo(DettPromo dettPromo);
}
