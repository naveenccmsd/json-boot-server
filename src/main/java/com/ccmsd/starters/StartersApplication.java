package com.ccmsd.starters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * SpringBoot entry point application
 *
 * @author Fabio Carvalho (facarvalho@paypal.com or fabiocarvalho777@gmail.com)
 */
@SpringBootApplication
@EnableJpaAuditing
public class StartersApplication extends SpringBootServletInitializer
{

	public static void main(String[] args)
	{
		SpringApplication.run(StartersApplication.class, args);
	}
}
