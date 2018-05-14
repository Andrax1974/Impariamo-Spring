package com.xantrix.webapp.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.xantrix.webapp.entities.Clienti;

//https://docs.oracle.com/javaee/7/tutorial/persistence-querylanguage.htm

@Repository
public class ClientiDaoImpl extends AbstractDao<Clienti, Integer> 
	implements ClientiDao
{

	@Override
	public Clienti SelByCodFidelity(String CodFidelity)
	{
		Clienti retVal;
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Clienti> queryDefinition = cb.createQuery(Clienti.class);
		
		Root<Clienti> recordset = queryDefinition.from(Clienti.class);
		
		queryDefinition.select(recordset).
						where(cb.equal(recordset.get("codFidelity"), CodFidelity));
		
		retVal = entityManager.createQuery(queryDefinition).getSingleResult();
		
		entityManager.clear();
		
		return retVal;
	}
	
	@Override
	public Clienti SelByUserId(String UserId)
	{
		
		Clienti retVal;	
		 
		String JPQL = "SELECT a FROM Clienti a JOIN a.utenti b WHERE b.userId = :userId ";
		 
		
		retVal = (Clienti) entityManager.createQuery(JPQL)
						   .setParameter("userId", UserId)
						   .getSingleResult();
		return retVal;
		
	}
	
	
	@Override
	public List<Clienti> SelByNominativo(String Nominativo)
	{
		CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Clienti> queryDefinition = queryBuilder.createQuery(Clienti.class);
		
		String ToSearch = "%" + Nominativo + "%";
		
		Root<Clienti> recordset = queryDefinition.from(Clienti.class);
		
		Expression<String> exp1 = queryBuilder.concat(recordset.<String>get("nome"), " ");
		exp1 = queryBuilder.concat(exp1, recordset.<String>get("cognome"));
		
		Expression<String> exp2 = queryBuilder.concat(recordset.<String>get("cognome"), " ");
		exp2 = queryBuilder.concat(exp2, recordset.<String>get("nome"));
		
		Predicate whereClause = queryBuilder.or(
				queryBuilder.like(exp1, ToSearch), 
				queryBuilder.like(exp2, ToSearch));
		
		queryDefinition.select(recordset).
						where(whereClause);
				
		return entityManager.createQuery(queryDefinition).getResultList();
	}
	
	//UTILIZZO JPQL
	@SuppressWarnings("unchecked")
	@Override
	public List<Clienti> SelByComune(String Comune)
	{
		List<Clienti> retVal;
		
		String JPQL = "SELECT a FROM Clienti a WHERE a.comune = :comune";
		
		retVal = entityManager.createQuery(JPQL)
				.setParameter("comune", Comune)
				.getResultList();
		
		return retVal;
	}
	
	//UTILIZZO JPQL
	@SuppressWarnings("unchecked")
	@Override
	public List<Clienti> SelByBollini(int NumBollini, String Tipo)
	{
		List<Clienti> retVal;
		
		String JPQL = "";
		
		if (Tipo.equals(">"))
			JPQL = "SELECT a FROM Clienti a JOIN a.card b WHERE b.bollini >= :qtabollini ORDER BY b.bollini DESC";
		else if (Tipo.equals("<"))
			JPQL = "SELECT a FROM Clienti a JOIN a.card b WHERE b.bollini <= :qtabollini ORDER BY b.bollini ASC";
		
		retVal = entityManager.createQuery(JPQL)
				.setParameter("qtabollini", NumBollini)
				.getResultList();
		
		return retVal;
	}
	
	@Override
	public String SelLastCodFid()
	{
		String retVal = "0";
		
		try
		{
			String JPQL = "SELECT a.codFidelity FROM Clienti a ORDER BY a.codFidelity DESC";
			
			retVal = (String) entityManager
					.createQuery(JPQL)
					.setMaxResults(1)
					.getSingleResult();
		}
		catch (Exception ex)
		{ }
		
		return retVal;
	}
	
	@Override
	public List<Clienti> SelTutti()
	{
		return super.SelTutti();
	}

	@Override
	public void Salva(Clienti cliente)
	{
		super.Inserisci(cliente);
	}
	
	@Override
	public void Aggiorna(Clienti cliente)
	{
		super.Aggiorna(cliente);
	}

	@Override
	public void Elimina(Clienti cliente)
	{
		super.Elimina(cliente);
	}


	@Override
	public long QtaTotBollini()
	{
		long retVal = 0;
		
		String JPQL = "SELECT SUM(b.bollini) FROM Clienti a JOIN a.card b ";
		
		retVal = (long) entityManager.createQuery(JPQL).getSingleResult();
		
		return retVal;
	}

}
