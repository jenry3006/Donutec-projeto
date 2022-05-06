package com.donutec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.donutec.conversor.ProdutoConverter;

@SpringBootApplication
public class HomerDonutsApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(HomerDonutsApplication.class, args);
	}
	
	@Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new ProdutoConverter());
    }

}
