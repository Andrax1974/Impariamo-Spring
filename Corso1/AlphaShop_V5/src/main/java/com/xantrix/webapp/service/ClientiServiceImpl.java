package com.xantrix.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xantrix.webapp.dao.ClientiDao;
import com.xantrix.webapp.entities.Clienti;

@Service
public class ClientiServiceImpl implements ClientiService
{

	@Autowired
	private ClientiDao clientirepository;

	@Transactional(readOnly = true)
	@Override
	public Clienti SelCliente(String CodFidelity)
	{
		Clienti cliente = clientirepository.SelById(CodFidelity);
		
		return cliente;
	}
}
