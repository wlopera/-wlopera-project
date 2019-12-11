package org.wlopera.project.dao.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que controla los mensajes de error.
 * 
 * @author Willian Lopera
 *
 */
@Getter
@AllArgsConstructor
public enum CrimsonLogicMessageErrorEnum {
	
	NO_EXIT_REGISTER_BY_ID("No existe registro de envio fitosanitario para este id: "),
	NO_EXIT_REGISTER_BY_GET_ALL("Error en la consulta de datos de envios fitosanitarios"),
	ERROR_CREATE_REGISTER("Error almacenando el envio fitosanitario a base de datos"),
	ERROR_DELETE_REGISTER("Error borrando el envio fitosanitario id: ");

	String id;

}
