package com.xantrix.webapp.converter;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

 
import com.xantrix.webapp.entities.Clienti;
import com.xantrix.webapp.entities.Utenti;
import com.xantrix.webapp.service.ClientiService;
import com.xantrix.webapp.service.UtentiService;
 

@Component
public class RoleToUtentiConverter implements Converter<Object, Utenti>
{

	@Autowired
	UtentiService utenteService;

	@Override
	public Utenti convert(Object element)
	{
		Utenti utente = null;

		try
		{
			String id = (String) element;

			utente = utenteService.SelByIdFidelity(id);

		} 
		catch (NoResultException ex)
		{ }

		return utente;
	}

}
