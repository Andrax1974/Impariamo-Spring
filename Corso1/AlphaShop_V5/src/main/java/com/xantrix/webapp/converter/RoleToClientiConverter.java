package com.xantrix.webapp.converter;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

 
import com.xantrix.webapp.entities.Clienti;
import com.xantrix.webapp.service.ClientiService;
 

@Component
public class RoleToClientiConverter implements Converter<Object, Clienti>
{

	@Autowired
	ClientiService clientiService;

	@Override
	public Clienti convert(Object element)
	{
		Clienti cliente = null;

		try
		{
			String id = (String) element;

			cliente = clientiService.SelCliente(id);

		} 
		catch (NoResultException ex)
		{ }

		return cliente;
	}

}
