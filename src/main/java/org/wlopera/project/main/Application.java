package org.wlopera.project.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Clase principal del aplicativo.
 * 	- Permite la inyeccion de los repositorios
 *  - Permite la definicion de paquetes (Scan) requeridos 
 * 
 * @author William Lopera
 */
@SpringBootApplication
@ComponentScan({"org.wlopera.project.*"})
@EntityScan("org.wlopera.project.dao.entity")
@EnableJpaRepositories("org.wlopera.project.dao.repository")
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
}