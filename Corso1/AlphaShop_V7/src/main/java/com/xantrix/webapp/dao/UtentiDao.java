package com.xantrix.webapp.dao;

import com.xantrix.webapp.entities.Utenti;

public interface UtentiDao
{
	Utenti SelByIdFidelity(String id);
	
	Utenti SelByUserId(String UserId);
	
	Utenti SelByUserIdCodFid(String UserId, String CodFid);
	
	void Salva(Utenti utente);
	
	void Aggiorna(Utenti utente);

	void Elimina(Utenti utente);
}
