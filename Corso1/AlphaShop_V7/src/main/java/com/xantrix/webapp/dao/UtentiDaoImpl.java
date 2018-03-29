package com.xantrix.webapp.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.xantrix.webapp.entities.Clienti;
import com.xantrix.webapp.entities.Utenti;

@Repository
public class UtentiDaoImpl extends AbstractDao<Utenti, Integer> 
	implements UtentiDao
{
	@Override
	public Utenti SelByIdFidelity(String idFidelity)
	{
		Utenti retVal = new Utenti();
		
		try
		{
			CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Utenti> queryDefinition = queryBuilder.createQuery(Utenti.class);
			
			Root<Utenti> recordset = queryDefinition.from(Utenti.class);
			
			queryDefinition.select(recordset).
							where(queryBuilder.equal(recordset.get("codFidelity"), idFidelity));
			
			retVal =  entityManager.createQuery(queryDefinition).getSingleResult();
			
			return retVal;
		}
		catch(Exception ex)
		{
			return retVal;
		}
	}
	
	@Override
	public Utenti SelByUserId(String UserId)
	{
		
		Utenti retVal;
		
		String JPQL = "SELECT a FROM Utenti a WHERE a.userId = :userId";
		
		retVal = (Utenti) entityManager.createQuery(JPQL)
						  .setParameter("userId", UserId)
						  .getSingleResult();	 
		
		return retVal;
	}
	
	@Override
	public Utenti SelByUserIdCodFid(String UserId, String CodFid)
	{
		Utenti retVal;
		
		String JPQL = "SELECT a FROM Utenti a WHERE a.userId = :userId AND codFidelity = :codFid";
		
		retVal = (Utenti) entityManager.createQuery(JPQL)
						  .setParameter("userId", UserId)
						  .setParameter("codFid", CodFid)
						  .getSingleResult();	 
		
		return retVal;
	}
	
	@Override
	public void Salva(Utenti utente)
	{
		super.Inserisci(utente);
	}
	
	@Override
	public void Aggiorna(Utenti utente)
	{
		super.Aggiorna(utente);
	}
	
	@Override
	public void Elimina(Utenti utente)
	{
		super.Elimina(utente);
	}

	

	

	

}
