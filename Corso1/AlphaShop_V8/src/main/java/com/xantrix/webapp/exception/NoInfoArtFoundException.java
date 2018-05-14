package com.xantrix.webapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Articolo Assente")
public class NoInfoArtFoundException extends RuntimeException
{
	private static final long serialVersionUID = 809850541299086904L;
	
	private String CodArt;

	public NoInfoArtFoundException(String codArt)
	{
		this.CodArt = codArt;
	}

	public String getCodArt()
	{
		return CodArt;
	}
	 
}
