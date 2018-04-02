package com.xantrix.webapp.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import com.xantrix.webapp.entities.Logins;

@Repository("persistentTokenRepository")
@Transactional
public class PersistentTokenDaoImp implements PersistentTokenRepository 
{
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public void createNewToken(PersistentRememberMeToken token)
	{
		Logins logins = new Logins();
		
		logins.setUserName(token.getUsername());
		logins.setSeries(token.getSeries());
		logins.setToken(token.getTokenValue());
		logins.setLast_used(token.getDate());
		
		this.entityManager.persist(logins);
		flushAndClear();

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId)
	{
		 
		String JPQL = "SELECT a FROM Logins a WHERE a.series = :series ";
		 
		
		Logins logins = (Logins) entityManager.createQuery(JPQL)
						   .setParameter("series", seriesId)
						   .getSingleResult();
		 
		if (logins != null) 
		{
		      return new PersistentRememberMeToken(
		    		  logins.getUserName(), 
		    		  logins.getSeries(), 
		    		  logins.getToken(),
		    		  logins.getLast_used());
		}
		
		return null;
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed)
	{
		String JPQL = "SELECT a FROM Logins a WHERE a.series = :series ";
		
		Logins logins = (Logins) entityManager.createQuery(JPQL)
				   .setParameter("series", series)
				   .getSingleResult();
		
		logins.setToken(tokenValue);
		logins.setLast_used(lastUsed);
		
		this.entityManager.merge(logins); 
		flushAndClear();
		
	}

	@Override
	public void removeUserTokens(String username)
	{
		// UTILIZZIAMO IL JPQL
		entityManager
		.createQuery("delete from Logins where username = :userId")
		.setParameter("userId", username)
		.executeUpdate();
		
		flushAndClear(); 
		
	}
	
	private void flushAndClear() 
	{
	    entityManager.flush();
	    entityManager.clear();
	}
	
}
