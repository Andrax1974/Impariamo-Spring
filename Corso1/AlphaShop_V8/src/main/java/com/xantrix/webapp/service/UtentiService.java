package com.xantrix.webapp.service;

import com.xantrix.webapp.entities.Utenti;

public interface UtentiService
{
	Utenti SelByIdFidelity(String idFidelity);
	
	void Salva(Utenti utente);

	void Aggiorna(Utenti utente);

	void Elimina(Utenti utente);
}
