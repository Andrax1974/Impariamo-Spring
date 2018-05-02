package com.xantrix.webapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@EnableWebMvc
@Configuration
public class MvcConfiguration implements WebMvcConfigurer
{
	@Bean
	@Description("Thymeleaf template resolver")
	public ClassLoaderTemplateResolver templateResolver()
	{

		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

		templateResolver.setPrefix("templates/");
		templateResolver.setCacheable(false);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCharacterEncoding("UTF-8");

		return templateResolver;
	}

	@Bean
	@Description("Thymeleaf template engine con integrazione Spring ")
	public SpringTemplateEngine templateEngine()
	{

		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());

		return templateEngine;
	}

	@Bean
	@Description("View Resolver Thymeleaf")
	public ViewResolver viewResolver()
	{

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();

		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setCharacterEncoding("UTF-8");

		return viewResolver;
	}
	
	private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
	{ "classpath:/static/", "classpath:/static/css/", "classpath:/static/js/" };

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		if (!registry.hasMappingForPattern("/static/**"))
		{
			registry
			.addResourceHandler("/static/**")
			.addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		}
	}
}
