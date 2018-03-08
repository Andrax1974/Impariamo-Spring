package com.xantrix.webapp.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.xantrix.webapp.entities.Clienti;

@Repository
public class ClientiDaoImpl extends AbstractDao<Clienti, Integer> 
	implements ClientiDao
{

	@Override
	public Clienti SelById(String id)
	{
		Clienti retVal;
		
		CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Clienti> queryDefinition = queryBuilder.createQuery(Clienti.class);
		
		Root<Clienti> recordset = queryDefinition.from(Clienti.class);
		
		queryDefinition.select(recordset).
						where(queryBuilder.equal(recordset.get("codFidelity"), id));
		
		retVal = entityManager.createQuery(queryDefinition).getSingleResult();
		
		entityManager.clear();
		
		return retVal;
	}
	
	//http://www.objectdb.com/java/jpa/query/jpql/string
	@Override
	public List<Clienti> SelByNome(String Nome, String OrderBy)
	{
		CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Clienti> queryDefinition = queryBuilder.createQuery(Clienti.class);
		
		String ToSearch = "%" + Nome + "%";
		
		Root<Clienti> recordset = queryDefinition.from(Clienti.class);
		
		queryDefinition.select(recordset).
						where(queryBuilder.like(recordset.get("descrizione"), ToSearch)).
						orderBy(queryBuilder.asc(recordset.get(OrderBy)));
		
		return entityManager.createQuery(queryDefinition).getResultList();
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

}
