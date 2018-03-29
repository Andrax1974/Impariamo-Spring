package com.xantrix.webapp.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter
{
	public static final String SPRING_SECURITY_FORM_DOMAIN_KEY = "domain";
	 
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException
	{
		
		if (!request.getMethod().equals("POST"))
		{ 
	       throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
	    } 
		
		UsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);
		setDetails(request, authRequest);
		
		return this.getAuthenticationManager().authenticate(authRequest);
		
		/*
		final String dbValue = request.getParameter("idFidParam");
		request.getSession().setAttribute("idFidParam", dbValue);

		return super.attemptAuthentication(request, response);
		*/
	}
	
	private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) 
	{
        String username = request.getParameter("userId");
        String password = obtainPassword(request);
        String codfid = request.getParameter("idFidParam");
 
        if (username == null) 
        {
            username = "";
        } 
        
        if (password == null) 
        {
            password = "";
        } 
        
        if (codfid == null) 
        {
        	codfid = "";
        } 
 
        //String usernameDomain = String.format("%s%s%s", username.trim(), String.valueOf(Character.LINE_SEPARATOR), domain);
        
        String usernameDomain = username.trim() + "@" + codfid;
        
        return new UsernamePasswordAuthenticationToken(usernameDomain, password);        
    } 
	
	/*
	private String obtainDomain(HttpServletRequest request) 
	{
        return request.getParameter(SPRING_SECURITY_FORM_DOMAIN_KEY);
    } 
    */
}
