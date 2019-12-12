package org.wlopera.project.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que controla los mensajes de error.
 * 
 * @author William Lopera
 *
 */
@Getter
@AllArgsConstructor
public enum CrimsonLogicMessageErrorEnum {
	
	NO_EXIT_REGISTER_BY_ID("No existe registro de envio fitosanitario para este id: "),
	NO_EXIT_REGISTER_BY_GET_ALL("Error en la consulta de datos de envios fitosanitarios"),
	ERROR_CREATE_REGISTER("Error almacenando el envio fitosanitario a base de datos"),
	ERROR_DELETE_REGISTER("Error borrando el envio fitosanitario id: "),
	ERROR_SAVE_ACK_REGISTER("Error guardando el acuse de recibo en base de datos: "),
	ERROR_SAVE_ERROR_REGISTER("Error guardando mensajes de error en base de datos: "),
	ERROR_SERVER("Error del servidor: "),
	ERROR_SERVER_UNKNOWN("No se obtuvo respuesta aceptable del servidor"),
	ERROR_DATE_FORMAT("El formato de fecha debe ser ddmmyy"),
	ERROR_SEQUENTIAL_SIZE("El secuencial debe ser de 6 caracteres");
	
	String id;

}
