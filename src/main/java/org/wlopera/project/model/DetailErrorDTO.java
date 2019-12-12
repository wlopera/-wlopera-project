package org.wlopera.project.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase negocio de datos con detalles de error retornados por la consulta.
 * 
 * @author William Lopera
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DetailErrorDTO implements Serializable {
	private static final long serialVersionUID = -1234923370992281068L;
	
	private String code;
	private String message;
}
