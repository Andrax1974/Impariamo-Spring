package com.xantrix.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xantrix.webapp.dao.Articoli;
import com.xantrix.webapp.repository.ArticoliRepository;

@Service
public class ArticoliServiceImpl implements ArticoliService
{

	@Autowired 
	ArticoliRepository articoliRepository;
	
	@Override
	public List<Articoli> SelByFilter(String Argomento1)
	{
		return articoliRepository.SelByFilter(Argomento1);
	} 

}
