package com.xantrix.webapp.domain;

import java.io.Serializable;

public class FamAssort implements Serializable
{
	private static final long serialVersionUID = 3788120361600509595L;
	
	private int Id;
	private String Descrizione;
	
	public int getId()
	{
		return Id;
	}
	
	public void setId(int id)
	{
		Id = id;
	}
	
	public String getDescrizione()
	{
		return Descrizione;
	}
	
	public void setDescrizione(String descrizione)
	{
		Descrizione = descrizione;
	}
		
}
