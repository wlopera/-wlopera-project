package org.wlopera.project.web.model;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Objeto 'ACK' del modelo de negocio.
 * 
 * @author William Lopera
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AckDTO {

	private Long id;

	private String transaccionId;

	private String code;

	private String message;

	private String date;

	private Set<ErrorMesaggeDTO> mensajesError;

}
