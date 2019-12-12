package org.wlopera.project.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wlopera.project.dao.mapper.CrimsonLogicDAOMapper;

/**
 * Clase que permite la inyeccion de objetos.
 * 
 * @author William Lopera
 */
@Configuration
public class ApplicationConfig {

	@Bean
	public CrimsonLogicDAOMapper getCrimsonLogicDAOMapper() {
		return new CrimsonLogicDAOMapper();
	}
}
