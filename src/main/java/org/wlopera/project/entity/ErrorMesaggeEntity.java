package org.wlopera.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "TBL_DETAIL_ERROR")
public class ErrorMesaggeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long errorId;

	@Column(name = "ack_id")
	private Long ackId;

	private String code;

	private String message;

}
