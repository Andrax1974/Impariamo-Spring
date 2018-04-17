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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "TIPOPROMO")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "id")
public class TipoPromo implements Serializable
{
	private static final long serialVersionUID = 8452515756703751450L;
	
	@Id
	@Column(name = "IDTIPOPROMO")
	private String idTipoPromo;
	
	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "tipoPromo")
	@JsonBackReference
	//@JsonIgnore
	private Set<DettPromo> dettPromo = new HashSet<>();
	
	public TipoPromo()
	{
	}

	public String getId()
	{
		return idTipoPromo;
	}

	public void setId(String idTipoPromo)
	{
		this.idTipoPromo = idTipoPromo;
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
