package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.entities.Clienti;

public interface ClientiService
{
	List<Clienti> SelTutti();
	
	Clienti SelCliente(String CodFidelity);

	void Salva(Clienti cliente);

	void Aggiorna(Clienti cliente);
}
