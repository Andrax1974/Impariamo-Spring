package com.xantrix.webapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xantrix.webapp.dao.Articoli;
 

public class ArticoliMapper  implements RowMapper<Articoli>
{

	@Override
	public Articoli mapRow(ResultSet row, int rowNum) throws SQLException
	{
		 Articoli articolo = new Articoli();
		
		 try
		 {
			 articolo.setCodArt(row.getString("CODART"));
			 articolo.setDescrizione(row.getString("DESCRIZIONE"));
			 articolo.setUm(row.getString("UM"));
			 articolo.setIdFamAss(row.getString("IDFAMASS"));
			 articolo.setDesFamAss(row.getString("DESFAMASS"));
			 articolo.setPrezzo(row.getDouble("PREZZO"));
			
		 }
		 catch (Exception ex)
		 {
			 System.out.println("Errore in ArticoliMapper.mapRow: " + ex);
		 }
		

		return articolo;
	}

}
