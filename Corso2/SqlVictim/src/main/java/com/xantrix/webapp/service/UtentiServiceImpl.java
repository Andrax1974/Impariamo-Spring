package com.xantrix.webapp.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xantrix.webapp.dao.Utenti;
import com.xantrix.webapp.repository.UtentiRepository;

@Service
public class UtentiServiceImpl implements UtentiService
{

	@Autowired 
	UtentiRepository utentiRepository;
	
	@Override
	public List<Utenti> SelByFilter(String Argomento1, String Argomento2)
	{
		return utentiRepository.SelByFilter(Argomento1, Argomento2);
	}

}
