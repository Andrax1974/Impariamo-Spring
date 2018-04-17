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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "PROMO")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "idPromo")
public class Promo implements Serializable
{
	private static final long serialVersionUID = -5905631309290304849L;
	
	@Id
	@Column(name = "IDPROMO")
	private String idPromo;
	
	@Column(name = "ANNO")
	private int anno;
	
	@Column(name = "CODICE")
	private String codice;
	
	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,  mappedBy = "promo", orphanRemoval = true)
	@JsonManagedReference
	private Set<DettPromo> dettPromo = new HashSet<>();
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,  mappedBy = "promo", orphanRemoval = true)
	@JsonManagedReference
	private Set<DepRifPromo> depRifPromo = new HashSet<>();
	
	public Promo() {}

	public String getIdPromo()
	{
		return idPromo;
	}

	public void setIdPromo(String idPromo)
	{
		this.idPromo = idPromo;
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
