package com.xantrix.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xantrix.webapp.dao.UtentiDao;
import com.xantrix.webapp.entities.Utenti;

@Service("utentiService")
@Transactional
public class UtentiServiceImpl implements UtentiService
{

	@Autowired
	private UtentiDao utentiRepository;
	
	@Override
	public void Salva(Utenti utente)
	{
		utentiRepository.Salva(utente);
	}

	@Override
	public void Aggiorna(Utenti utente)
	{
		utentiRepository.Aggiorna(utente);
	}

	@Override
	public void Elimina(Utenti utente)
	{
		utentiRepository.Elimina(utente);
	}

	@Override
	public Utenti SelByIdFidelity(String idFidelity)
	{
		return utentiRepository.SelByIdFidelity(idFidelity);
	}

}
