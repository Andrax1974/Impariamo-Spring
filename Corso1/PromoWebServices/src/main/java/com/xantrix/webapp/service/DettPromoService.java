package com.xantrix.webapp.service;

import java.util.List;
import java.util.Optional;

import com.xantrix.webapp.entities.DettPromo;
 

public interface DettPromoService
{
	Optional<DettPromo>  SelDettPromo(long Id);
	
	List<DettPromo> SelDettPromoByCodFid(String CodFid);
	
	void InsDettPromo(DettPromo dettPromo);
	
	void UpdDettPromo(Long Id, String Oggetto);
}
