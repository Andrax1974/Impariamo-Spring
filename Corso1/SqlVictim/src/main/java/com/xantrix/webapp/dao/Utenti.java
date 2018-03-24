package com.xantrix.webapp.dao;

import java.io.Serializable;

public class Utenti implements Serializable
{
	private static final long serialVersionUID = -3024838048479473150L;
	
	private String codFidelity;
	private String userId;
	private String pwd;
	private String abilitato;
	
	public Utenti()
	{}
	
	public String getCodFidelity()
	{
		return codFidelity;
	}
	
	public void setCodFidelity(String codFidelity)
	{
		this.codFidelity = codFidelity;
	}
	
	public String getUserId()
	{
		return userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	
	public String getPwd()
	{
		return pwd;
	}
	
	public void setPwd(String pwd)
	{
		this.pwd = pwd;
	}
	
	public String getAbilitato()
	{
		return abilitato;
	}
	
	public void setAbilitato(String abilitato)
	{
		this.abilitato = abilitato;
	}
	
	
	

}
