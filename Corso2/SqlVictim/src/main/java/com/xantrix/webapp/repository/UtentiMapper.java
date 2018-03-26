package com.xantrix.webapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.xantrix.webapp.dao.Utenti;

public class UtentiMapper implements RowMapper<Utenti>
{
	public Utenti mapRow(ResultSet row, int rowNum) throws SQLException
	{
		Utenti utenti = new Utenti();
		
		 try
		 {
			 utenti.setUserId(row.getString("USERID"));
			 utenti.setPwd(row.getString("PASSWORD"));
			 utenti.setCodFidelity(row.getString("CODFIDELITY"));
			 utenti.setAbilitato(row.getString("ABILITATO"));
			  
			
		 }
		 catch (Exception ex)
		 {
			 System.out.println("Errore in UtentiMapper.mapRow: " + ex);
		 }
		
 
		return utenti;
	}
}
