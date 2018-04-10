package com.xantrix.webapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROMO")
public class Promo implements Serializable
{
	private static final long serialVersionUID = -5905631309290304849L;
	
	@Id
	@Column(name = "Id")
	private String id;
	
	@Column(name = "Anno")
	private int anno;
	
	@Column(name = "Codice")
	private String codice;
	
	@Column(name = "Descrizione")
	private String descrizione;
	
	public Promo()
	{
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public int getAnno()
	{
		return anno;
	}

	public void setAnno(int anno)
	{
		this.anno = anno;
	}

	public String getCodice()
	{
		return codice;
	}

	public void setCodice(String codice)
	{
		this.codice = codice;
	}

	public String getDescrizione()
	{
		return descrizione;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}
	
	

}
