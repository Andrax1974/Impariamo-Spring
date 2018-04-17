package com.xantrix.webapp.exception;

public class PromoException extends Exception
{
	private static final long serialVersionUID = 4642884385580726415L;

	private String messaggio;

	public PromoException()
	{
		super();
	}

	public PromoException(String Messaggio)
	{
		super(Messaggio);
		this.messaggio = Messaggio;
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
