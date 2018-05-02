package com.xantrix.webapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xantrix.webapp.entities.DettPromo;
 

public interface DettPromoRepository extends JpaRepository<DettPromo, Long>
{
	@Query(value = "EXEC Sp_SelActivePromoFid :idpromo", nativeQuery = true)
	List<DettPromo> findDettPromoByCodFid(@Param("idpromo") String CodFid);
	
	@Modifying
	@Query(value = "UPDATE DETTPROMO SET OGGETTO = :oggetto WHERE ID = :id", nativeQuery = true)
	void UpdOggettoPromo(@Param("oggetto") String Oggetto, @Param("id") Long Id);
	
	@Modifying
	@Query(value = "DELETE FROM DETTPROMO WHERE ID = :id", nativeQuery = true)
	void DelRowPromo(@Param("id") Long Id);
}
