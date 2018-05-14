package com.xantrix.webapp.service;

import java.util.List;
import java.util.Set;

import com.xantrix.webapp.entities.Profili;

public interface ProfiliService
{
	Profili SelById(int id);
	
	List<Profili> SelByIdFidelity(String IdFidelity);
	
	void Salva(Profili profilo);

	void Elimina(Profili profilo);
	
	void Aggiorna(Profili profilo);
}
