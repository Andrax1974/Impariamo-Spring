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

import lombok.Data;

@Entity
@Table(name = "UTENTI", uniqueConstraints =
{ @UniqueConstraint(columnNames = "CODFIDELITY") })
@Data
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
	
}



