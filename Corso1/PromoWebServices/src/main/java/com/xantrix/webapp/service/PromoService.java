package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.entities.Promo;

public interface PromoService
{
	public List<Promo> SelTutti();
	
	public Promo SelByIdPromo(String IdPromo);
	
	public Promo SelByCodice(String Anno, String Codice);
	
	public List<Promo> SelActivePromo();
	
	public void InsPromo(Promo promo);
	
	public void DelPromo(Promo promo);
	
}
