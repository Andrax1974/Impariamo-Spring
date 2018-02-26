package com.xantrix.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xantrix.webapp.domain.Clienti;
import com.xantrix.webapp.repository.ClientiRepository;

@Service
public class ClientiServiceImpl implements ClientiService
{

	@Autowired
	private ClientiRepository clientirepository;

	@Transactional(readOnly = true)
	@Override
	public Clienti SelCliente(String CodFidelity)
	{
		Clienti cliente = clientirepository.findByCodFidelity(CodFidelity);
		
		return cliente;
	}
}
