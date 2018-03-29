package com.xantrix.webapp.repository;

import java.util.List;

import com.xantrix.webapp.domain.Articoli;

public interface ArticoliRepository
{
	List <Articoli> SelArticoliByFilter(String Filtro);
	
	List <Articoli> SelArticoliByFilter(String Filtro, String OrderBy, String Tipo);
		
	void InsArticolo(Articoli articolo);
	
	void DelArticolo(String CodArt);
}
