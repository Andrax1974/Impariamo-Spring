package com.xantrix.webapp.dao;

import java.io.Serializable;

public class Articoli  implements Serializable
{
	private static final long serialVersionUID = 1873918481992516900L;
	
	private String codArt;
	private String descrizione;
	private String um;
	private String idFamAss;
	private String desFamAss;
	private double prezzo;
	
	public Articoli()
	{}
	
	public String getCodArt()
	{
		return codArt;
	}
	
	public void setCodArt(String codArt)
	{
		this.codArt = codArt;
	}
	
	public String getDescrizione()
	{
		return descrizione;
	}
	
	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}
	
	public String getUm()
	{
		return um;
	}
	
	public void setUm(String um)
	{
		this.um = um;
	}
	
	public String getIdFamAss()
	{
		return idFamAss;
	}
	
	public void setIdFamAss(String idFamAss)
	{
		this.idFamAss = idFamAss;
	}
	
	public String getDesFamAss()
	{
		return desFamAss;
	}
	
	public void setDesFamAss(String desFamAss)
	{
		this.desFamAss = desFamAss;
	}
	
	public double getPrezzo()
	{
		return prezzo;
	}
	
	public void setPrezzo(double prezzo)
	{
		this.prezzo = prezzo;
	}
	
	
	

}
