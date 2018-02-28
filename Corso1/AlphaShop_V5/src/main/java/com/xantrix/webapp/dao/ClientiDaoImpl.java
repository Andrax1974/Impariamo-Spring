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

	private Root<Clienti> recordset;
	
	@Override
	public Clienti SelById(String id)
	{
		CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Clienti> queryDefinition = queryBuilder.createQuery(Clienti.class);
		
		recordset = queryDefinition.from(Clienti.class);
		
		queryDefinition.select(recordset).
						where(queryBuilder.equal(recordset.get("codFidelity"), id));
		
		return entityManager.createQuery(queryDefinition).getSingleResult();
	}
	
	//http://www.objectdb.com/java/jpa/query/jpql/string
	@Override
	public List<Clienti> SelByNome(String Nome, String OrderBy)
	{
		CriteriaBuilder queryBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Clienti> queryDefinition = queryBuilder.createQuery(Clienti.class);
		
		String ToSearch = "%" + Nome + "%";
		
		recordset = queryDefinition.from(Clienti.class);
		
		queryDefinition.select(recordset).
						where(queryBuilder.like(recordset.get("descrizione"), ToSearch)).
						orderBy(queryBuilder.asc(recordset.get(OrderBy)));
		
		return entityManager.createQuery(queryDefinition).getResultList();
	}

	@Override
	public void Salva(Clienti cliente)
	{
		 super.Inserisci(cliente);
	}
	
	@Override
	public void Aggiorna(Clienti cliente)
	{
		entityManager.remove(cliente);
	}

	@Override
	public void Elimina(Clienti cliente)
	{
		entityManager.remove(cliente);
	}

}
