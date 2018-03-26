package com.xantrix.webapp.repository;

import java.util.List;

import com.xantrix.webapp.dao.Articoli;
 
public interface ArticoliRepository
{
	List <Articoli> SelByFilter(String Argomento1);
}
