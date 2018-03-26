package com.xantrix.webapp.repository;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xantrix.webapp.dao.Articoli;
 
@Repository
public class ArticoliRepositoryImpl implements ArticoliRepository
{

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Articoli> SelByFilter(String Argomento1)
	{
		List<Articoli> Articoli = null;
		
		try
		{
			String Sql = "SELECT " + 
						"A.CODART," + 
						"A.DESCRIZIONE," + 
						"A.UM," + 
						"A.IDFAMASS," + 
						"B.DESCRIZIONE AS DESFAMASS," + 
						"AA.Prezzo, " +
						"ISNULL(A.PZCART,0) AS PZCART, " +
						"A.PESONETTO " +
						"FROM " + 
						"[dbo].[ARTICOLI] A " + 
						"JOIN [dbo].[FAMASSORT] B ON A.IDFAMASS = B.ID " + 
						"JOIN (SELECT CODART, PREZZO FROM [dbo].[DETTLISTINI] WHERE IDLIST = 1) AA " + 
						"ON A.CODART = AA.CodArt " + 
						"WHERE A.DESCRIZIONE LIKE '%" + Argomento1 + "%' ORDER BY CODART;";
		
			log.info(Sql);
			
			Articoli = jdbcTemplate.query(Sql, new ArticoliMapper());
		}
		catch (Exception ex)
		{
			log.error("Errore: " + ex.getMessage());
		}
		
		return Articoli;
	}

}
