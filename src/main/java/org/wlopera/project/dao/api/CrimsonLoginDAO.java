package org.wlopera.project.dao.api;

import java.util.List;

import org.wlopera.project.dao.exception.CrimsonLogicException;
import org.wlopera.project.web.model.AckDTO;
import org.wlopera.project.web.model.ErrorMesaggeDTO;

/**
 * Api - CRUD - Base de datos.
 * 
 * @author Willian Lopera
 */
public interface CrimsonLoginDAO {

	/**
	 * Permite consultar todos los Envio de Certificado Fitosanitarios.
	 * 
	 * @return Lista de Envios de certificados fitosanitarios
	 * 
	 * @throws CrimsonLogicException Excepcion en caso de falla
	 */
	List<AckDTO> getAcks() throws CrimsonLogicException;

	/**
	 * Permite consultar un Envio de Certificado Fitosanitario segun su
	 * identificador.
	 * 
	 * @param id Identificador del Envio de Certificado Fitosanitario
	 * 
	 * @return Envio de certificado fitosanitario requerido
	 * 
	 * @throws CrimsonLogicException Excepcion en caso de falla
	 */
	AckDTO getAckById(Long id) throws CrimsonLogicException;

	/**
	 * Permite crear un acuse de recibo
	 * 
	 * @param ack Envio de Certificado Fitosanitario
	 * 
	 * @return Acuse de recibo generado
	 * 
	 * @throws CrimsonLogicException Excepcion en caso de falla
	 */
	AckDTO createAck(AckDTO ack) throws CrimsonLogicException;

	/**
	 * Permite crear mensaje de error
	 * 
	 * @param error Mensaje de error
	 * @param ackId Identificador del acuse de recibo
	 * 
	 * @return Mensaje de error
	 * 
	 * @throws CrimsonLogicException Excepcion en caso de falla
	 */
	ErrorMesaggeDTO createError(ErrorMesaggeDTO error, Long ackId) throws CrimsonLogicException;

	/**
	 * Permite eliminar un Envio Fitosaitario de la BD.
	 * 
	 * @param id Identificador del Certificado del Envio Fitosanotario a eliminar
	 * 
	 * @throws CrimsonLogicException Excepcion en caso de falla
	 */
	void deleteAck(Long id) throws CrimsonLogicException;

}
