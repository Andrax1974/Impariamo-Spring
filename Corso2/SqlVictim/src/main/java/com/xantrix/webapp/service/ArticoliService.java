package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.dao.Articoli;

public interface ArticoliService
{
	List <Articoli> SelByFilter(String Argomento1);
}
