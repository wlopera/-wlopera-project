package org.wlopera.project.command;

import org.springframework.ui.Model;
import org.wlopera.project.exception.CrimsonLogicException;
import org.wlopera.project.web.model.RegistryDTO;

/**
 * Clase con valores de la peticion.
 * 
 * @author William Lopera
 */
public interface RegistryCommand {

	/**
	 * Metodo que ejecuta la peticion.
	 * 
	 * @param registry Registro con datos de la peticion
	 * @param model Modelo del negocio
	 * 
	 * @return true/false.
	 * 
	 * @throws CrimsonLogicException En caso de error.
	 */
	boolean execute(final RegistryDTO registry, final Model model) throws CrimsonLogicException;
}
