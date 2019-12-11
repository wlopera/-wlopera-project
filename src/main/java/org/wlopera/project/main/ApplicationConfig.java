package org.wlopera.project.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wlopera.project.dao.mapper.CrimsonLogicDAOMapper;

@Configuration
public class ApplicationConfig {

	@Bean
	public CrimsonLogicDAOMapper getCrimsonLogicDAOMapper() {
		return new CrimsonLogicDAOMapper();
	}
}
