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

import lombok.Data;

@Entity
@Table(name = "CARDS")
@Data
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
}
