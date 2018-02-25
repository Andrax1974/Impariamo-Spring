package com.xantrix.webapp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENTI")
public class Clienti  implements Serializable
{
	private static final long serialVersionUID = -7414142881348723650L;

	@Id
	@Column(name = "CODFIDELITY")
	private String IdFidelity;
	
	@Column(name = "NOME", nullable = false)
	private String Nome;
	
	@Column(name = "COGNOME", nullable = false)
	private String Cognome;
	
	@Column(name = "INDIRIZZO", nullable = true)
	private String Indirizzo;
	
	@Column(name = "COMUNE", nullable = true)
	private String Comune;
	
	@Column(name = "CAP", nullable = true)
	private String Cap;
	
	@Column(name = "PROV", nullable = true)
	private String Prov;
	
	@Column(name = "TELEFONO", nullable = true)
	private String Telefono;
	
	@Column(name = "MAIL", nullable = true)
	private String Mail;
	
	@Column(name = "STATO", nullable = true)
	private String Stato;

	@Column(name = "DATACREAZ", nullable = true)
	private Date DataCreaz;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	private Set<Utenti> Utenti = new HashSet<>();

	public String getIdFidelity()
	{
		return IdFidelity;
	}

	public void setIdFidelity(String idFidelity)
	{
		IdFidelity = idFidelity;
	}

	public String getNome()
	{
		return Nome;
	}

	public void setNome(String nome)
	{
		Nome = nome;
	}

	public String getCognome()
	{
		return Cognome;
	}

	public void setCognome(String cognome)
	{
		Cognome = cognome;
	}

	public String getIndirizzo()
	{
		return Indirizzo;
	}

	public void setIndirizzo(String indirizzo)
	{
		Indirizzo = indirizzo;
	}

	public String getComune()
	{
		return Comune;
	}

	public void setComune(String comune)
	{
		Comune = comune;
	}

	public String getCap()
	{
		return Cap;
	}

	public void setCap(String cap)
	{
		Cap = cap;
	}

	public String getProv()
	{
		return Prov;
	}

	public void setProv(String prov)
	{
		Prov = prov;
	}

	public String getTelefono()
	{
		return Telefono;
	}

	public void setTelefono(String telefono)
	{
		Telefono = telefono;
	}

	public String getMail()
	{
		return Mail;
	}

	public void setMail(String mail)
	{
		Mail = mail;
	}

	public String getStato()
	{
		return Stato;
	}

	public void setStato(String stato)
	{
		Stato = stato;
	}

	public Date getDataCreaz()
	{
		return DataCreaz;
	}

	public void setDataCreaz(Date dataCreaz)
	{
		DataCreaz = dataCreaz;
	}

	public Set<Utenti> getUtenti()
	{
		return Utenti;
	}

	public void setUtenti(Set<Utenti> utenti)
	{
		Utenti = utenti;
	}
	
	
}
