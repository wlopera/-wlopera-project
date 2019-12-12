package org.wlopera.project.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Objeto 'Mensaje de error' del modelo de negocio.
 * 
 * @author William Lopera
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ErrorMesaggeDTO {

	private Long id;

	private String code;

	private String message;

}
