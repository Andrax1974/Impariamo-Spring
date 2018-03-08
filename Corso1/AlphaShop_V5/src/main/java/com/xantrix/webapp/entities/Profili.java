package com.xantrix.webapp.entities;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROFILI")
public class Profili implements Serializable
{
	private static final long serialVersionUID = -3270956277534858122L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Basic(optional = false)
	private String tipo;
		
	@ManyToOne
	@JoinColumn(name = "CODFIDELITY", referencedColumnName = "codFidelity")
	private Utenti utente;
	
	public Profili() {}
	
	public Profili(String Tipo, Utenti Utente)
	{
		this.tipo = Tipo;
		this.utente = Utente;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public Utenti getUtente()
	{
		return utente;
	}

	public void setUtente(Utenti utente)
	{
		this.utente = utente;
	}

}
