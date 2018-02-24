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

@XmlRootElement
public class Articoli implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int Riga;
	
	@NotEmpty(message = "{NotNull.Articoli.CodArt.validation}")
	@Size(min=4, max=20, message="{Size.Articoli.CodArt.validation}")
	private String CodArt;
	
	@Size(min=8, max=60, message="{Size.Articoli.Descrizione.validation}")
	private String Descrizione;	
	
	private Double Prezzo;
	private Double PrezzoKg;
	
	@NotNull(message= "{NotNull.Articoli.Um.validation}") 
	private String Um;
	
	private String CodStat;
	
	@Max(99)
	@Digits(integer=2, fraction=0, message="{Digits.Articoli.PzCart.validation}")
	private int PzCart;

	@Digits(integer=4, fraction=3, message="{Digits.Articoli.PesoNetto.validation}")
	private double PesoNetto;
	private float QtaMag;
	
	@NotNull(message= "{NotNull.Articoli.IdIva.validation}") 
	private int IdIva;
	
	@NotNull(message= "{NotNull.Articoli.IdStatoArt.validation}") 
	private String IdStatoArt;
	
	private Date DataCreaz;
	
	@NotNull(message= "{NotNull.Articoli.IdFamAss.validation}") 
	private int IdFamAss;
	private String DesFamAss;
	
	@JsonIgnore
	private MultipartFile Immagine; 
	
	public String getCodArt()
	{
		return CodArt;
	}
	
	public void setCodArt(String codArt)
	{
		CodArt = codArt;
	}
	
	public String getDescrizione()
	{
		return Descrizione;
	}
	
	public void setDescrizione(String descrizione)
	{
		Descrizione = descrizione;
	}
	
	public String getUm()
	{
		return Um;
	}
	
	public void setUm(String um)
	{
		Um = um;
	}
	
	public String getCodStat()
	{
		return CodStat;
	}
	
	public void setCodStat(String codStat)
	{
		CodStat = codStat;
	}
	public int getPzCart()
	{
		return PzCart;
	}
	
	public void setPzCart(int pzCart)
	{
		PzCart = pzCart;
	}
	
	public double getPesoNetto()
	{
		return PesoNetto;
	}
	
	public void setPesoNetto(double pesoNetto)
	{
		PesoNetto = pesoNetto;
	}
	
	public int getIdIva()
	{
		return IdIva;
	}
	
	public void setIdIva(int idIva)
	{
		IdIva = idIva;
	}
	
	public String getIdStatoArt()
	{
		return IdStatoArt;
	}
	
	public void setIdStatoArt(String idStatoArt)
	{
		IdStatoArt = idStatoArt;
	}
	
	public Date getDataCreaz()
	{
		return DataCreaz;
	}
	
	public void setDataCreaz(Date dataCreaz)
	{
		DataCreaz = dataCreaz;
	}
	
	public int getIdFamAss()
	{
		return IdFamAss;
	}
	
	public void setIdFamAss(int idFamAss)
	{
		IdFamAss = idFamAss;
	}

	public int getRiga()
	{
		return Riga;
	}

	public void setRiga(int riga)
	{
		Riga = riga;
	}

	public Double getPrezzo()
	{
		return Prezzo;
	}

	public void setPrezzo(Double prezzo)
	{
		Prezzo = prezzo;
	}

	public float getQtaMag()
	{
		return QtaMag;
	}

	public void setQtaMag(float qtaMag)
	{
		QtaMag = qtaMag;
	}

	public String getDesFamAss()
	{
		return DesFamAss;
	}

	public void setDesFamAss(String desFamAss)
	{
		DesFamAss = desFamAss;
	}

	
	public Double getPrezzoKg()
	{
		return PrezzoKg;
	}

	public void setPrezzoKg(Double prezzoKg)
	{
		PrezzoKg = prezzoKg;
	}

	@XmlTransient
	public MultipartFile getImmagine()
	{
		return Immagine;
	}

	public void setImmagine(MultipartFile immagine)
	{
		Immagine = immagine;
	}
	
	
	
	
}
