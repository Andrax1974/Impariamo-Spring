package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PROMO")
public class Promo implements Serializable
{
	private static final long serialVersionUID = -5905631309290304849L;
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "ANNO")
	private int anno;
	
	@Column(name = "CODICE")
	private String codice;
	
	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,  mappedBy = "promo", orphanRemoval = true)
	private Set<DettPromo> dettPromo = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,  mappedBy = "promo", orphanRemoval = true)
	private Set<DepRifPromo> depRifPromo = new HashSet<>();
	
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

	public Set<DettPromo> getDettPromo()
	{
		return dettPromo;
	}

	public void setDettPromo(Set<DettPromo> dettPromo)
	{
		this.dettPromo = dettPromo;
	}

	public Set<DepRifPromo> getDepRifPromo()
	{
		return depRifPromo;
	}

	public void setDepRifPromo(Set<DepRifPromo> depRifPromo)
	{
		this.depRifPromo = depRifPromo;
	}
	
	

}
