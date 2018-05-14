package com.xantrix.webapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.xantrix.webapp.domain.FamAssort;

public class FamAssMapper implements RowMapper<FamAssort>
{
	@Override
	public FamAssort mapRow(ResultSet row, int rowNum) throws SQLException
	{
		FamAssort famAssort = new FamAssort();
		
		 try
		 {
			famAssort.setId(row.getInt("Id"));  
			famAssort.setDescrizione(row.getString("Descrizione").trim());
		 }
		 catch (Exception ex)
		 {
			 System.out.println("Errore in FamAssMapper.mapRow: " + ex);
		 }
	
		return famAssort;
	
	}

}
