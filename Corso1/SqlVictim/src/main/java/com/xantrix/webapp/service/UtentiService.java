package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.dao.Utenti;

public interface UtentiService
{
	List <Utenti> SelByFilter(String Argomento1, String Argomento2);
}
