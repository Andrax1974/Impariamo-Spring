package com.xantrix.webapp.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PromoFidInterceptor extends HandlerInterceptorAdapter
{
	 private String codeFidelity; 
     private String errorRedirect; 
     private String promoRedirect; 
     
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException
	{
		String CodeFidelity = request.getParameter("fidelity");

		if (CodeFidelity.equals(codeFidelity))
		{
			response.sendRedirect(request.getContextPath() + "/" + promoRedirect);
		} 
		else
		{
			response.sendRedirect(errorRedirect);
		}

		return false;
	}

	public String getCodeFidelity()
	{
		return codeFidelity;
	}

	public void setCodeFidelity(String codeFidelity)
	{
		this.codeFidelity = codeFidelity;
	}

	public String getErrorRedirect()
	{
		return errorRedirect;
	}

	public void setErrorRedirect(String errorRedirect)
	{
		this.errorRedirect = errorRedirect;
	}

	public String getOfferRedirect()
	{
		return promoRedirect;
	}

	public void setOfferRedirect(String offerRedirect)
	{
		this.promoRedirect = offerRedirect;
	}
	
	
}
