package com.xantrix.webapp.dao;

import com.xantrix.webapp.entities.Utenti;

public interface UtentiDao
{
	Utenti SelByIdFidelity(String id);
	
	void Salva(Utenti utente);
	
	void Aggiorna(Utenti utente);

	void Elimina(Utenti utente);
}
