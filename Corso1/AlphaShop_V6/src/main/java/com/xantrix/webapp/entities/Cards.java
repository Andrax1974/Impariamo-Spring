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
@Table(name = "CARDS")
public class Cards implements Serializable
{
	private static final long serialVersionUID = -3751231307546162427L;
	
	@Id
	@Column(name = "CODFIDELITY")
	private String codFidelity;
	
	@Column(name = "BOLLINI")
	private Integer bollini;
	
	@Column(name = "ULTIMASPESA")
	private Date ultimaSpesa;
	
	@Column(name = "OBSOLETO")
	private String obsoleto;
	
	@OneToOne(mappedBy = "card")
	private Clienti cliente;
	
	
	public Cards() {}
	
	public Cards(String CodFid, Integer Bollini)
	{
		this.codFidelity = CodFid;
		this.bollini = Bollini;
	}
	
	public String getCodFidelity()
	{
		return codFidelity;
	}

	public void setCodFidelity(String codFidelity)
	{
		this.codFidelity = codFidelity;
	}

	public Integer getBollini()
	{
		return (bollini == null) ? 0 : bollini;
	}

	public void setBollini(Integer bollini)
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
