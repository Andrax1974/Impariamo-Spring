package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.entities.Clienti;

public interface ClientiService
{
	List<Clienti> SelTutti();
	
	List<Clienti> SelByNominativo(String Nominativo);
	
	List<Clienti> SelByComune(String Comune);
	
	List<Clienti> SelByBollini(int NumBollini, String Tipo);
	
	String SelLastCodFid();
	
	Clienti SelCliente(String CodFidelity);
	
	long QtaTotBollini();
	
	void Salva(Clienti cliente);

	void Aggiorna(Clienti cliente);
	
	void Elimina(Clienti cliente);
}
