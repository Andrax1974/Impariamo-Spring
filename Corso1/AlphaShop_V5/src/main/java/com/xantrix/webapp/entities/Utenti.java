package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "UTENTI", uniqueConstraints =
{ @UniqueConstraint(columnNames = "CODFIDELITY") })
public class Utenti implements Serializable
{
	private static final long serialVersionUID = 8473057964112587082L;

	@Id
	@Column(name = "CODFIDELITY",unique = true, nullable = false, length = 20)
	private String codFidelity;
	
	@Column(name = "USERID",nullable = false, length = 50)
	private String userId;

	@Column(name = "PASSWORD", nullable = false, length = 30)
	private String pwd;

	@Column(name = "ABILITATO", nullable = false,  length = 2)
	private String abilitato;

	@OneToOne
	@PrimaryKeyJoinColumn
	private Clienti clienti;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,  mappedBy = "utente", orphanRemoval = true)
	private Set<Profili> profili = new HashSet<>();

	public Utenti()
	{
	}
	
	public Utenti(String CodFidelity)
	{
		this.codFidelity = CodFidelity;
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
	
	public String getCodFidelity()
	{
		return codFidelity;
	}

	public void setCodFidelity(String codFidelity)
	{
		this.codFidelity = codFidelity;
	}

	public Clienti getClienti()
	{
		return clienti;
	}

	public void setClienti(Clienti clienti)
	{
		this.clienti = clienti;
	}

	public Set<Profili> getProfili()
	{
		return profili;
	}

	public void setProfili(Set<Profili> profili)
	{
		this.profili = profili;
	}	
	
	
}



/*
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "ID", unique = true, nullable = false)
private int id;
*/

/*
@ManyToOne
@JoinColumn(name = "CODFIDELITY", referencedColumnName = "CODFIDELITY", nullable = false)
@JsonBackReference
private Clienti clienti;
*/

/*
public String getCodFidelity()
{
	return codFidelity;
}

public void setCodFidelity(String codFidelity)
{
	this.codFidelity = codFidelity;
}

/*
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	*/

