package org.wlopera.project.dao.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@Table(name = "TBL_ACKNOWLEDGMENT_RECEIPT")
public class AckEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ackId;

	@Column(name = "transaccion_id")
	private String transaccionId;

	@Column()
	private String code;

	@Column()
	private String message;

	@Column()
	private String date;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ack_id")
	@Fetch(value = FetchMode.SUBSELECT)
	private Set<ErrorMesaggeEntity> mensajesError;

}
