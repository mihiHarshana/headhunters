package com;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@ComponentScan(basePackages = { "com.controller" })
@EntityScan("com.model")
@ComponentScan({"com.dao", "com.service"})
@EnableWebMvc
@SpringBootApplication
public class Application extends SpringBootServletInitializer{


	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(Application.class);
	    }
	public static void main(String args[]) throws IOException{		
		SpringApplication.run(Application.class, args);		
	}
/*
	@Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
        return hemf.getSessionFactory();
    }*/
}
