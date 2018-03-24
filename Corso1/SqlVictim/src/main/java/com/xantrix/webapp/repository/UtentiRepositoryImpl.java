package com.xantrix.webapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xantrix.webapp.dao.Utenti;

@Repository
public class UtentiRepositoryImpl implements UtentiRepository
{

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
    private JdbcTemplate jdbcTemplate;
	 
	@Override
	public List<Utenti> SelByFilter(String Argomento1, String Argomento2)
	{
		
		String Sql = "SELECT * FROM [dbo].[UTENTI] " + 
					 "WHERE [USERID] = '" + Argomento1 + "'" + 
					 " AND [PASSWORD] = '" + Argomento2 + "'";
		
		 log.info(Sql);
		
		List<Utenti> articoli = jdbcTemplate.query(Sql, new UtentiMapper());
		
		return articoli;
	}

}
