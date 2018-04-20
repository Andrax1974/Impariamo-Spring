package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "DETTPROMO")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "idPromo")
public class DettPromo implements Serializable
{
	private static final long serialVersionUID = 7444232363326102441L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "RIGA")
	private int riga;
	
	@Column(name = "CODART")
	private String codart;
	
	@Column(name = "CODFID")
	private String codfid;
		
	@Column(name = "INIZIO")
	@Temporal(TemporalType.DATE)
	private Date inizio;
	
	@Column(name = "FINE")
	@Temporal(TemporalType.DATE)
	private Date fine;
	
	@Column(name = "OGGETTO")
	private String oggetto;
	
	@Column(name = "ISFID")
	private String isfid;
	
	@ManyToOne
	@JoinColumn(name = "IDPROMO", referencedColumnName = "idPromo")
	@JsonBackReference 
	//@JsonIgnore
	private Promo promo;
	
	@ManyToOne
	@JoinColumn(name = "IDTIPOPROMO", referencedColumnName = "idTipoPromo")
	private TipoPromo tipoPromo;
	
	public DettPromo() {}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getRiga()
	{
		return riga;
	}

	public void setRiga(int riga)
	{
		this.riga = riga;
	}

	public String getCodart()
	{
		return codart;
	}

	public void setCodart(String codart)
	{
		this.codart = codart;
	}

	public String getCodfid()
	{
		return codfid;
	}

	public void setCodfid(String codfid)
	{
		this.codfid = codfid;
	}

	public Date getInizio()
	{
		return inizio;
	}

	public void setInizio(Date inizio)
	{
		this.inizio = inizio;
	}

	public Date getFine()
	{
		return fine;
	}

	public void setFine(Date fine)
	{
		this.fine = fine;
	}

	public String getOggetto()
	{
		return oggetto;
	}

	public void setOggetto(String oggetto)
	{
		this.oggetto = oggetto;
	}
	
	public String getIsfid()
	{
		return isfid;
	}

	public void setIsfid(String isfid)
	{
		this.isfid = isfid;
	}

	public Promo getPromo()
	{
		return promo;
	}

	public void setPromo(Promo promo)
	{
		this.promo = promo;
	}

	public TipoPromo getTipoPromo()
	{
		return tipoPromo;
	}

	public void setTipoPromo(TipoPromo tipoPromo)
	{
		this.tipoPromo = tipoPromo;
	}

}
