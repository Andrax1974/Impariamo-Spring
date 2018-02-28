package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
@Table(name = "CLIENTI",  uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODFIDELITY")})
public class Clienti  implements Serializable
{
	private static final long serialVersionUID = -7414142881348723650L;

	@Id
	@Column(name = "CODFIDELITY",unique = true, nullable = false)
	private String codFidelity;
	
	@Column(name = "NOME", nullable = false)
	private String nome;
	
	@Column(name = "COGNOME", nullable = false)
	private String cognome;
	
	@Column(name = "INDIRIZZO", nullable = true)
	private String indirizzo;
	
	@Column(name = "COMUNE", nullable = true)
	private String comune;
	
	@Column(name = "CAP", nullable = true)
	private String cap;
	
	@Column(name = "PROV", nullable = true)
	private String prov;
	
	@Column(name = "TELEFONO", nullable = true)
	private String telefono;
	
	@Column(name = "MAIL", nullable = true)
	private String mail;
	
	@Column(name = "STATO", nullable = true)
	private String stato;

	@Column(name = "DATACREAZ", nullable = true)
	private Date dataCreaz;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "clienti", cascade = CascadeType.ALL)
	private Set<Utenti> utenti = new HashSet<>();
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Cards card;
	
	public Clienti() { }

	public String getCodFidelity()
	{
		return codFidelity;
	}

	public void setCodFidelity(String codFidelity)
	{
		this.codFidelity = codFidelity;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getCognome()
	{
		return cognome;
	}

	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}

	public String getIndirizzo()
	{
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo)
	{
		this.indirizzo = indirizzo;
	}

	public String getComune()
	{
		return comune;
	}

	public void setComune(String comune)
	{
		this.comune = comune;
	}

	public String getCap()
	{
		return cap;
	}

	public void setCap(String cap)
	{
		this.cap = cap;
	}

	public String getProv()
	{
		return prov;
	}

	public void setProv(String prov)
	{
		this.prov = prov;
	}

	public String getTelefono()
	{
		return telefono;
	}

	public void setTelefono(String telefono)
	{
		this.telefono = telefono;
	}

	public String getMail()
	{
		return mail;
	}

	public void setMail(String mail)
	{
		this.mail = mail;
	}

	public String getStato()
	{
		return stato;
	}

	public void setStato(String stato)
	{
		this.stato = stato;
	}

	public Date getDataCreaz()
	{
		return dataCreaz;
	}

	public void setDataCreaz(Date dataCreaz)
	{
		this.dataCreaz = dataCreaz;
	}

	public Set<Utenti> getUtenti()
	{
		return utenti;
	}

	public void setUtenti(Set<Utenti> utenti)
	{
		this.utenti = utenti;
	}

	public Cards getCard()
	{
		return card;
	}

	public void setCard(Cards card)
	{
		this.card = card;
	}
	
	
}
