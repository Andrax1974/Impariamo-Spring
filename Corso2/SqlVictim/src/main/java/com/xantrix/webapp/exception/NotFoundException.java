package com.xantrix.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Nessun Articolo Trovato")
public class NotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 809850541299086904L;
	
	private String Parametro;

	public NotFoundException(String parametro)
	{
		this.Parametro = parametro;
	}

	public String getParametro()
	{
		return Parametro;
	}

	public void setParametro(String parametro)
	{
		Parametro = parametro;
	}

	 
	 
}
