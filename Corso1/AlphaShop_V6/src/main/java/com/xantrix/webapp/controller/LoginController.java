package com.xantrix.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/form")
public class LoginController
{
	@RequestMapping
	public String getLogin(Model model)
	{
		return "login";
	}
}
