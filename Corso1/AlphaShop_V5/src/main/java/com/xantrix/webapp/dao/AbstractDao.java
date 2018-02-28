package com.xantrix.webapp.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;

public abstract class AbstractDao<I extends Serializable, Id extends Serializable>
	implements GenericRepository<I, Id>
{
	@PersistenceContext
	protected EntityManager entityManager;
	
	protected final Class<I> entityClass;
	
	@SuppressWarnings("unchecked")
	public AbstractDao()
	{
		this.entityClass = (Class<I>) ((ParameterizedType) 
				this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public List<I> SelTutti()
	{
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<I> query = builder.createQuery(this.entityClass);

        return this.entityManager.createQuery(
        		query.select(query.from(this.entityClass))).getResultList();
	}
	
	//consultare http://docs.oracle.com/javaee/6/tutorial/doc/gjivm.html
	@Override
	public I SelById(Id id)
	{
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<I> query = builder.createQuery(this.entityClass);
			
		return this.entityManager.createQuery(
					query.where(
							builder.equal(
									query.from(this.entityClass).
									get("id"), id))).
					getSingleResult();
			
	}
	
	@Override
	public void Inserisci(I entity)
	{
		this.entityManager.persist(entity);
	}
	
	@Override
	public void Aggiorna(I entity)
	{
		this.entityManager.merge(entity);
	}
	
	@Override
	public void Elimina(I entity)
	{
		this.entityManager.remove(entity);
	}
	
	@Override
	public void EliminaById(Id id)
	{
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaDelete<I> query = builder.createCriteriaDelete(this.entityClass);
		
		this.entityManager.createQuery(
				query.where(
						builder.equal(
								query.from(this.entityClass)
								.get("id"), id)
        )).executeUpdate();
	}
}
