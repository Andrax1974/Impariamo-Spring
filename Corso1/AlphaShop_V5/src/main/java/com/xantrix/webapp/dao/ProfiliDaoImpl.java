package com.xantrix.webapp.dao;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.xantrix.webapp.entities.Profili;
 
 
@Repository
public class ProfiliDaoImpl extends AbstractDao<Profili, Integer> 
implements ProfiliDao
{
	@Override
	public Profili SelById(int id)
	{
		return super.SelById(id);
	}

	
	@Override
	public List<Profili> SelByIdFidelity(String IdFidelity)
	{
		List<Profili> recordset = super.SelTutti();
		
		recordset = recordset.stream()
				.filter(u -> IdFidelity.contains(u.getUtente().getCodFidelity()))
				.collect(Collectors.toList());
		
		return recordset;
	}
	
	@Override
	public void Salva(Profili profilo)
	{
		 super.Inserisci(profilo);
	}

	@Override
	public void Aggiorna(Profili profilo)
	{
		super.Aggiorna(profilo);
		
	}
	
	@Override
	public void Elimina(Profili profilo)
	{
		//UTILIZZIAMO IL JPQL
		entityManager.createQuery("delete from Profili where id = :id")
		  .setParameter("id", profilo.getId())
		  .executeUpdate();
	}
	

}
