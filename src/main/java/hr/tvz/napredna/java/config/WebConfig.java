package hr.tvz.napredna.java.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;

import hr.tvz.napredna.java.model.converters.ModelToKorisnikConverter;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
public class WebConfig {

	@SuppressWarnings("rawtypes")
	@Bean
	public ConversionServiceFactoryBean conversionService() {
		ConversionServiceFactoryBean factory = new ConversionServiceFactoryBean();
		Set<Converter> converters = new HashSet<>();
		
		converters.add(new ModelToKorisnikConverter());
		
		factory.setConverters(converters);
		return factory;
	}
	
	@Bean
	public TemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addDialect(new LayoutDialect());
		
		return templateEngine;
	}
	
}
