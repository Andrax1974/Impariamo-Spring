package com.xantrix.webapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "UTENTI", uniqueConstraints = {
		@UniqueConstraint(columnNames = "ID")})
public class Utenti implements Serializable
{
	private static final long serialVersionUID = 8473057964112587082L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private int id;
	
	@Column(name = "USERID")
	private String userId;

	@Column(name = "PASSWORD", nullable = false)
	private String pwd;

	@Column(name = "ABILITATO", nullable = false)
	private String abilitato;
	
	@ManyToOne
	@JoinColumn(name = "CODFIDELITY", referencedColumnName = "CODFIDELITY", nullable = false)
	@JsonBackReference
	private Clienti clienti;
	
	public Utenti() { }

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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

	public Clienti getClienti()
	{
		return clienti;
	}

	public void setClienti(Clienti clienti)
	{
		this.clienti = clienti;
	}
}
