package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

//https://docs.oracle.com/javaee/7/tutorial/bean-validation001.htm

@Entity
@Table(name = "CLIENTI")
public class Clienti  implements Serializable
{
	private static final long serialVersionUID = -7414142881348723650L;

	@Id
	@Column(name = "CODFIDELITY")
	@NotNull(message = "{NotNull.Clienti.codFidelity.validation}")
	@Size(min=8, max=20, message = "{Size.Clienti.codFidelity.validation}")
	private String codFidelity;
	
	@Column(name = "NOME")
	@Size(min=2, max=50, message = "{Size.Clienti.nome.validation}")
	private String nome;
	
	@Column(name = "COGNOME")
	@Size(min=2, max=60, message = "{Size.Clienti.cognome.validation}")
	private String cognome;
	
	@Column(name = "INDIRIZZO")
	@Size(min=10, max=80, message = "{Size.Clienti.indirizzo.validation}")
	private String indirizzo;
	
	@Column(name = "COMUNE")
	@Size(min=2, max=50, message = "{Size.Clienti.comune.validation}")
	private String comune;
	
	@Column(name = "CAP")
	private String cap;
	
	@Column(name = "PROV")
	private String prov;
	
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Column(name = "MAIL")
	private String mail;
	
	@Column(name = "STATO")
	private String stato;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATACREAZ")
	private Date dataCreaz;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Cards card;
	
	@OneToOne(mappedBy = "clienti", cascade = CascadeType.ALL, orphanRemoval = true)
	private Utenti utenti;
	
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

	
	public Utenti getUtenti()
	{
		return utenti;
	}

	public void setUtenti(Utenti utenti)
	{
		this.utenti = utenti;
	}
	

	public Cards getCard()
	{
		return  card;
	}

	public void setCard(Cards card)
	{
		this.card = card;
	}
	
}


/*
@OneToMany(fetch = FetchType.EAGER, mappedBy = "clienti", cascade = CascadeType.ALL)
private List<Utenti> utenti;
*/