package com.xantrix.webapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xantrix.webapp.entities.Promo;

@Repository
public interface PromoRepository extends JpaRepository<Promo, Long>
{
	Promo findByIdPromo(String IdPromo);
	
	Promo findByAnnoAndCodice(int Anno, String Codice);
	
	@Query("select a from Promo a join a.dettPromo b where ?1 between b.inizio and b.fine")
	List<Promo> findActivePromo(Date Data);
	
	
	 
}
