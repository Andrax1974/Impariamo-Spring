package com.xantrix.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xantrix.webapp.dao.ClientiDao;
import com.xantrix.webapp.entities.Clienti;

@Service("clientiService")
@Transactional
public class ClientiServiceImpl implements ClientiService
{

	@Autowired
	private ClientiDao clientiRepository;

	@Override
	public Clienti SelCliente(String CodFidelity)
	{
		Clienti cliente = clientiRepository.SelById(CodFidelity);
		
		return cliente;
	}
	
	@Override
	public void Salva(Clienti cliente)
	{
		clientiRepository.Salva(cliente);
	}

	@Override
	public void Aggiorna(Clienti cliente)
	{
		clientiRepository.Aggiorna(cliente);
	}

	@Override
	public List<Clienti> SelTutti()
	{
		return clientiRepository.SelTutti();
	}

	
}
