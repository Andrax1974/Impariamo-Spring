package com.xantrix.webapp.config.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	
	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService;
	
	
	@Autowired
	@Qualifier("persistentTokenRepository")
	private PersistentTokenRepository persistentTokenRepository;
	
	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;
	
	@Autowired
    DataSource dataSource;
 	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	};
	
	@Override
	public void configure(final AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
	}

	@Bean
	public HttpFirewall allowUrlEncodedSlashHttpFirewall() 
	{
	    StrictHttpFirewall firewall = new StrictHttpFirewall();
	    firewall.setAllowUrlEncodedSlash(true);
	    firewall.setAllowSemicolon(true);
	    
	    return firewall;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception 
	{
	  super.configure(web);
	  
	  web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
	 
	}
	
	private static final String[] CLIENTI_MATCHER =
	{
			"/clienti/aggiungi/**",
			"/clienti/modifica/**",
			"/clienti/elimina/**"
	};
	
	@Override
	protected void configure(final HttpSecurity http) throws Exception
	{
			http
				.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/login/**").permitAll()
				.antMatchers("/").hasAnyRole("ANONYMOUS", "USER")
				.antMatchers(CLIENTI_MATCHER).access("hasRole('ADMIN')")
				//.antMatchers("/clienti/aggiungi/**").access("hasRole('ADMIN')")
				//.antMatchers("/clienti/modifica/**").access("hasRole('ADMIN')")
				//.antMatchers("/clienti/elimina/**").access("hasRole('ADMIN')")
				.antMatchers("/clienti/**").hasRole("USER")
				.and()
				.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
					.formLogin()
						.loginPage("/login/form")
						.loginProcessingUrl("/login")
						.failureUrl("/login/form?error")
						.usernameParameter("userId")
						.passwordParameter("password")
				.and()
					.exceptionHandling()
						.accessDeniedPage("/login/form?forbidden")
				.and()
					.logout()
						.permitAll()
						.logoutUrl("/login/form?logout")
						.invalidateHttpSession(true)
						.logoutSuccessHandler(customLogoutSuccessHandler)
                        .clearAuthentication(true);
					//.and()
					//.cors().disable()
					//.csrf().disable();
				
					
					
	}

	@Bean
	public PersistentTokenBasedRememberMeServices customRememberMeServices()
	{
			String Key = "ricordamiKey";
			
			PersistentTokenBasedRememberMeServices rememberMeServices = 
	      			new CustomPersistentTokenBasedRememberMeServices(Key, userDetailsService, persistentTokenRepository);
	      	
	      	rememberMeServices.setCookieName("ricordami");
	      	rememberMeServices.setTokenValiditySeconds(60 * 60 * 4);
	      	rememberMeServices.setParameter("ricordami");
	      	rememberMeServices.setUseSecureCookie(false);
	      	
	      	return rememberMeServices;
	}
	
	  
	public AuthenticationFilter authenticationFilter() throws Exception 
	{
        AuthenticationFilter filter = new AuthenticationFilter();
        
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationFailureHandler(failureHandler());
        filter.setAuthenticationSuccessHandler(savedRequestAwareAuthenticationSuccessHandler());
        filter.setRememberMeServices(customRememberMeServices());
        
        return filter;
    } 
	
	public SimpleUrlAuthenticationFailureHandler failureHandler() 
	{ 
        return new SimpleUrlAuthenticationFailureHandler("/login/form?error"); 
    } 
	
	/*
	@Bean
    public PersistentTokenRepository persistentTokenRepository() 
	{
        JdbcTokenRepositoryImpl tokenRepositoryImpl = new JdbcTokenRepositoryImpl();
        tokenRepositoryImpl.setDataSource(dataSource);
        
        return tokenRepositoryImpl;
    }
    */
    
	@Bean
	public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() 
	{
        SavedRequestAwareAuthenticationSuccessHandler auth = new SavedRequestAwareAuthenticationSuccessHandler();
		auth.setTargetUrlParameter("targetUrl");
		
		return auth;
	}	
	
	
}
