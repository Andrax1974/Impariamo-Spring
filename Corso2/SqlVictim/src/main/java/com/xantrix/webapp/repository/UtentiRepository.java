package com.xantrix.webapp.repository;

import java.util.List;

import com.xantrix.webapp.dao.Utenti;

public interface UtentiRepository
{
	List <Utenti> SelByFilter(String Argomento1, String Argomento2);
}
