package com.xantrix.webapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xantrix.webapp.domain.Articoli;

public class ArticoliMapper implements RowMapper<Articoli>
{
	public Articoli mapRow(ResultSet row, int rowNum) throws SQLException
	{
		Articoli articoli = new Articoli();
		
		 try
		 {
			articoli.setRiga(row.getInt("RIGA"));  
			articoli.setCodArt(row.getString("CODART").trim());
			articoli.setDescrizione(row.getString("DESCRIZIONE").trim());
			articoli.setPrezzo(row.getDouble("PREZZO"));
			articoli.setUm(row.getString("UM"));
			articoli.setCodStat(row.getString("CODSTAT").trim()); 
			articoli.setPzCart(row.getInt("PZCART"));
			articoli.setPesoNetto(row.getDouble("PESONETTO"));
			articoli.setIdIva(row.getInt("IDIVA"));
			articoli.setQtaMag(row.getFloat("QTAMAG"));  
			articoli.setIdStatoArt(row.getString("IDSTATOART").trim());
			articoli.setIdFamAss(row.getInt("IDFAMASS"));
			articoli.setDesFamAss(row.getString("FAMASS").trim());
			articoli.setDataCreaz(row.getDate("DATACREAZIONE"));
			articoli.setPrezzoKg(row.getDouble("PRZKG"));
			
		 }
		 catch (Exception ex)
		 {
			 System.out.println("Errore in ArticoliMapper.mapRow: " + ex);
		 }
		
 
		return articoli;
	}
}
