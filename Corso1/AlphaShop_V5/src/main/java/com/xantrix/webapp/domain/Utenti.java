package com.xantrix.webapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "UTENTI")
public class Utenti implements Serializable
{
	private static final long serialVersionUID = 8473057964112587082L;

	@Id
	@Column(name = "ID")
	private int Id;

	@ManyToOne
	@JoinColumn(name = "CODFIDELITY")
	private Clienti cliente;

	@Column(name = "USERID", nullable = false)
	private String UserId;

	@Column(name = "PASSWORD", nullable = false)
	private String Pwd;

	@Column(name = "ABILITATO", nullable = false)
	private String Abilitato;

	public int getId()
	{
		return Id;
	}

	public void setId(int id)
	{
		Id = id;
	}

	public String getUserId()
	{
		return UserId;
	}

	public void setUserId(String userId)
	{
		UserId = userId;
	}

	public String getPwd()
	{
		return Pwd;
	}

	public void setPwd(String pwd)
	{
		Pwd = pwd;
	}

	public String getAbilitato()
	{
		return Abilitato;
	}

	public void setAbilitato(String abilitato)
	{
		Abilitato = abilitato;
	}

	public Clienti getCliente()
	{
		return cliente;
	}

	public void setCliente(Clienti cliente)
	{
		this.cliente = cliente;
	}
	
	

}
