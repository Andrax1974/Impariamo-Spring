package com.xantrix.webapp.dao;

import java.util.List;

import com.xantrix.webapp.entities.Clienti;

public interface ClientiDao  
{
	Clienti SelByCodFidelity(String CodFidelity);
	
	Clienti SelByUserId(String UserId);
	
	List<Clienti> SelTutti();
	
	List<Clienti> SelByComune(String Comune);
	
	List<Clienti> SelByNominativo(String Nome);
	
	List<Clienti> SelByBollini(int NumBollini, String Tipo);
	
	String SelLastCodFid();
	
	long QtaTotBollini();

	void Salva(Clienti cliente);

	void Aggiorna(Clienti cliente);

	void Elimina(Clienti cliente);

}
