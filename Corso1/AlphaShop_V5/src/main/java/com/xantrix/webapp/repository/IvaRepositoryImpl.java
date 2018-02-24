package com.xantrix.webapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.xantrix.webapp.domain.Iva;

@Repository
public class IvaRepositoryImpl implements IvaRepository
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Iva> SelIva()
	{
		String Sql = "SELECT A.* FROM IVA A ORDER BY ALIQUOTA ASC;";
		
		List<Iva> iva = jdbcTemplate.query(Sql, new IvaMapper());
		
		return iva;
	} 

}
