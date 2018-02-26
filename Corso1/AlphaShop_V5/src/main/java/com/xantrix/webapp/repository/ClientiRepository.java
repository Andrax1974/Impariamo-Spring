package com.xantrix.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xantrix.webapp.domain.Clienti;

@Repository
public interface ClientiRepository extends JpaRepository<Clienti, Long>
{
	Clienti findByCodFidelity(String CodFidelity);
}
