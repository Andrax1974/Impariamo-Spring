package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.entities.Clienti;

public interface ClientiService
{
	List<Clienti> SelTutti();
	
	List<Clienti> SelByNominativo(String Nominativo);
	
	Clienti SelCliente(String CodFidelity);
	
	void Salva(Clienti cliente);

	void Aggiorna(Clienti cliente);
	
	void Elimina(Clienti cliente);
}
