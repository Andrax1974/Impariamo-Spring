package com.xantrix.webapp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.xantrix.webapp.domain.Iva;

public class IvaMapper implements RowMapper<Iva>
{
	@Override
	public Iva mapRow(ResultSet row, int rowNum) throws SQLException
	{
		Iva iva = new Iva();

		try
		{
			iva.setId(row.getInt("IDIVA"));
			iva.setDescrizione(row.getString("DESCRIZIONE").trim());
			iva.setAliquota(row.getInt("ALIQUOTA"));
		} 
		catch (Exception ex)
		{
			System.out.println("Errore in IvaMapper.mapRow: " + ex);
		}

		return iva;

	}
}
