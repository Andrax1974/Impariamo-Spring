package com.xantrix.webapp.exception;

public class ErrorResponse
{
	private int codice;
	private String messaggio;
	
	public int getCodice()
	{
		return codice;
	}
	
	public void setCodice(int codice)
	{
		this.codice = codice;
	}
	
	public String getMessaggio()
	{
		return messaggio;
	}
	
	public void setMessaggio(String messaggio)
	{
		this.messaggio = messaggio;
	}
}
