package com.xantrix.webapp.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "DEPRIFPROMO")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "idPromo")
public class DepRifPromo  implements Serializable
{
private static final long serialVersionUID = 1436206967746080890L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "IDDEPOSITO")
	private int idDeposito;
	
	@ManyToOne
	@JoinColumn(name = "IDPROMO", referencedColumnName = "idPromo")
	@JsonBackReference 
	//@JsonIgnore
	private Promo promo;
	
	public DepRifPromo() {}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getIdDeposito()
	{
		return idDeposito;
	}

	public void setIdDeposito(int idDeposito)
	{
		this.idDeposito = idDeposito;
	}

	public Promo getPromo()
	{
		return promo;
	}

	public void setPromo(Promo promo)
	{
		this.promo = promo;
	}
}
