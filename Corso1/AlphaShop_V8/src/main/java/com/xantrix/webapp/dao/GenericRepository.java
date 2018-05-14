package com.xantrix.webapp.dao;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public interface GenericRepository<I extends Serializable, E extends Serializable>
{
	@NotNull
	List<I> SelTutti();
	
	void Inserisci(@NotNull I entity);
	
	void Aggiorna(@NotNull I entity);
	
	void Elimina(@NotNull I entity);
	
	void EliminaById(@NotNull E Id);
	
	I SelById(@NotNull E Id);
}
