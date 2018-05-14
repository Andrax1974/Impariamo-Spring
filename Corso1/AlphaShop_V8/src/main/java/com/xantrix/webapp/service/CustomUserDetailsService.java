package com.xantrix.webapp.service;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xantrix.webapp.entities.Utenti;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService
{

	@Autowired
	private UtentiService utenteService;
	 
	 
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String UserId) 
			throws UsernameNotFoundException
	{
		
		 String[] usernameAndDomain = StringUtils.split(UserId, "@");
		 
		 if (usernameAndDomain == null || usernameAndDomain.length != 2) 
		 {
	            throw new UsernameNotFoundException("Username and domain must be provided"); 
	     } 
		 
		 String userId = usernameAndDomain[0];
		 String codFid = usernameAndDomain[1];
		 
		 Utenti utente = utenteService.SelByUserIdCodFid(userId, codFid);
		 
		 
		 //Utenti utente = utenteService.SelByUserId(UserId);
		
		 if (utente == null)
		 {
			 throw new UsernameNotFoundException("Utente non Trovato!!");
		 }
		
		 UserBuilder builder = null;
		 
		 builder = org.springframework.security.core.userdetails.User.withUsername(utente.getUserId());
		 builder.disabled((utente.getAbilitato().equals("Si") ? false : true ));
		 builder.password(utente.getPwd());
		 
		 String[] profili = utente.getProfili()
				 .stream().map(a -> "ROLE_" + a.getTipo()).toArray(String[]::new);
		 
		 builder.authorities(profili);
		 
		 return builder.build();
		 
	}
	
}
