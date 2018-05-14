package com.xantrix.webapp.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/login/form")
public class LoginController
{
	private static final Logger logger = LogManager.getLogger("Logout");
	
	@Autowired
	@Qualifier("persistentTokenRepository")
    private PersistentTokenRepository persistentTokenRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getLogin()
	{
		return "login";
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public String getLoginPost(HttpServletRequest request, HttpServletResponse response)
	{
		
		String[] test = request.getParameterValues("logout");
		
		if (test != null)
		{
			Cookie cookieWithSlash = new Cookie("JSESSIONID", null);
	        //Tomcat adds extra slash at the end of context path (e.g. "/foo/")
	        cookieWithSlash.setPath(request.getContextPath() + "/"); 
	        cookieWithSlash.setMaxAge(0); 

	        Cookie cookieWithoutSlash = new Cookie("JSESSIONID", null);
	        //JBoss doesn't add extra slash at the end of context path (e.g. "/foo")
	        cookieWithoutSlash.setPath(request.getContextPath()); 
	        cookieWithoutSlash.setMaxAge(0); 

	        //Remove cookies on logout so that invalidSessionURL (session timeout) is not displayed on proper logout event
	        response.addCookie(cookieWithSlash); //For cookie added by Tomcat 
	        response.addCookie(cookieWithoutSlash); //For cookie added by JBoss
	        
	        if (test.length == 2)
	        {
	        	 logger.info("utente: " + test[1]); 
				 persistentTokenRepository.removeUserTokens(test[1]);
	        }
	        
		}
		
		return "redirect:/login/form?logout";
	}
	
}
