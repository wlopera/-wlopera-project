package org.wlopera.project.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Modelo de base de datos.
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
