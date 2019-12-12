package org.wlopera.project.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase negocio de datos con el acuse de recibo consultado.
 * 
 * @author William Lopera
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AcknowledgmentReceiptDTO implements Serializable {
	private static final long serialVersionUID = 9080450287797634428L;

	private HeadDTO head;
	private String code;
	private String message;
	private String date;
	private ErrorInfoDTO errorInfo;
}