package com.xantrix.webapp.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@XmlRootElement
@Data
public class Articoli implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int riga;
	
	@NotNull(message = "{NotNull.Articoli.CodArt.validation}")
	@Size(min=4, max=20, message="{Size.Articoli.CodArt.validation}")
	private String codArt;
	
	@Size(min=8, max=60, message="{Size.Articoli.Descrizione.validation}")
	private String descrizione;	
	
	private Double prezzo;
	private Double prezzoKg;
	
	@NotNull(message= "{NotNull.Articoli.Um.validation}") 
	private String um;
	
	private String codStat;
	
	@Max(99)
	@Digits(integer=2, fraction=0, message="{Digits.Articoli.PzCart.validation}")
	private int pzCart;

	@Digits(integer=4, fraction=3, message="{Digits.Articoli.PesoNetto.validation}")
	private double pesoNetto;
	private float qtaMag;
	
	@NotNull(message= "{NotNull.Articoli.IdIva.validation}") 
	private int idIva;
	
	@NotNull(message= "{NotNull.Articoli.IdStatoArt.validation}") 
	private String idStatoArt;
	
	private Date dataCreaz;
	
	@NotNull(message= "{NotNull.Articoli.IdFamAss.validation}") 
	private int idFamAss;
	
	private String desFamAss;
	
	@JsonIgnore
	@XmlTransient
	private MultipartFile immagine; 
	
}
