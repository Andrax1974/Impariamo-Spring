package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "CARDS",  uniqueConstraints = {
		@UniqueConstraint(columnNames = "CODFIDELITY")})
public class Cards implements Serializable
{
	private static final long serialVersionUID = -3751231307546162427L;
	
	@Id
	@Column(name = "CODFIDELITY",unique = true, nullable = false)
	private String codFidelity;
	
	@Column(name = "BOLLINI")
	private int bollini;
	
	@Column(name = "ULTIMASPESA")
	private Date ultimaSpesa;
	
	@Column(name = "OBSOLETO")
	private String obsoleto;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "card", cascade = CascadeType.ALL)
	private Clienti cliente;

	public String getCodFidelity()
	{
		return codFidelity;
	}

	public void setCodFidelity(String codFidelity)
	{
		this.codFidelity = codFidelity;
	}

	public int getBollini()
	{
		return bollini;
	}

	public void setBollini(int bollini)
	{
		this.bollini = bollini;
	}

	public Date getUltimaSpesa()
	{
		return ultimaSpesa;
	}

	public void setUltimaSpesa(Date ultimaSpesa)
	{
		this.ultimaSpesa = ultimaSpesa;
	}

	public String getObsoleto()
	{
		return obsoleto;
	}

	public void setObsoleto(String obsoleto)
	{
		this.obsoleto = obsoleto;
	}

	public Clienti getCliente()
	{
		return cliente;
	}

	public void setCliente(Clienti cliente)
	{
		this.cliente = cliente;
	}
	
	

}