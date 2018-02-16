package com.xantrix.webapp.domain;

import java.io.Serializable;

public class Iva implements Serializable
{
	 
	private static final long serialVersionUID = 4157166221401288115L;
	
	private int Id;
	private String Descrizione;
	private int Aliquota;
	
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
	
	public int getAliquota()
	{
		return Aliquota;
	}
	
	public void setAliquota(int aliquota)
	{
		Aliquota = aliquota;
	}
	
	
	
}
