package com.xantrix.webapp.domain;

import java.io.Serializable;
import java.util.Date;

public class Articoli implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int Riga;
	private String CodArt;
	private String Descrizione;	
	private Double Prezzo;
	private Double PrezzoKg;
	private String Um;
	private String CodStat;
	private int PzCart;
	private double PesoNetto;
	private float QtaMag;
	private int IdIva;
	private String IdStatoArt;
	private Date DataCreaz;
	private int IdFamAss;
	private String DesFamAss;
	
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
	
	
	
	
}
