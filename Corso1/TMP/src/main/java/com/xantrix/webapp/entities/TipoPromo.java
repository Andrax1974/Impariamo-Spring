package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "TIPOPROMO")
public class TipoPromo implements Serializable
{
	private static final long serialVersionUID = 8452515756703751450L;
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tipoPromo")
	@JsonManagedReference
	private Set<DettPromo> dettPromo = new HashSet<>();
	
	public TipoPromo()
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
	
	
	

}
