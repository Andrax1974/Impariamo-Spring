package com.xantrix.webapp.dao;

import java.util.List;

import com.xantrix.webapp.entities.Clienti;

public interface ClientiDao  
{
	Clienti SelById(String id);

	List<Clienti> SelTutti();

	List<Clienti> SelByNome(String Nome, String OrderBy);

	void Salva(Clienti cliente);

	void Aggiorna(Clienti cliente);

	void Elimina(Clienti cliente);

}
