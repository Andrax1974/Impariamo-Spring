package com.xantrix.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xantrix.webapp.dao.Articoli;
import com.xantrix.webapp.exception.NotFoundException;
import com.xantrix.webapp.service.ArticoliService;

@Controller
@RequestMapping("/articoli")
public class ArticoliController
{
	
	@Autowired 
	ArticoliService articoliService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String GetUtenti(Model model)
	{	
		return "articoli";	 
	}
	
	@RequestMapping(value = "/cerca", method = RequestMethod.GET)
	public String GetArticoliByFilter(@RequestParam("Par1") String Argomento1,
			Model model)
	{

		List<Articoli> recordset = articoliService.SelByFilter(Argomento1); 
		
		if (recordset == null || recordset.isEmpty())
			throw new NotFoundException(Argomento1); 
			
		model.addAttribute("Par1", Argomento1);
		model.addAttribute("Articoli", recordset);	
			
		return "articoli";
		 
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleError(HttpServletRequest request, NotFoundException exception)
	{
		ModelAndView mav = new ModelAndView();

		mav.addObject("parametro", exception.getParametro());
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL() + "?" + request.getQueryString());
		
		mav.setViewName("noInfoArt");

		return mav;
	}
}
