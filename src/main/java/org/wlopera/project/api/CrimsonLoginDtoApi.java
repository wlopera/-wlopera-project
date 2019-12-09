package org.wlopera.project.api;

import java.util.List;

import org.wlopera.project.entity.ACKEntity;
import org.wlopera.project.entity.ErrorMesaggeEntity;
import org.wlopera.project.exception.CrimsonLogicException;

/**
 * Api - CRUD - Base de datos.
 * 
 * @author Willian Lopera
 */
public interface CrimsonLoginDtoApi {

	/**
	 * Permite consultar todos los Envio de Certificado Fitosanitarios.
	 * 
	 * @return Lista de Envios de certificados fitosanitarios
	 * 
	 * @throws CrimsonLogicException Excepcion en caso de falla
	 */
	List<ACKEntity> getAcks() throws CrimsonLogicException;

	/**
	 * Permite consultar un Envio de Certificado Fitosanitario segun su
	 * identificador.
	 * 
	 * @return Envio de certificado fitosanitario requerido
	 * 
	 * @throws CrimsonLogicException Excepcion en caso de falla
	 */
	ACKEntity getAckById(Long id) throws CrimsonLogicException;

	/**
	 * Permite crear un acuse de recibo
	 * 
	 * @return Acuse de recibo generado
	 * 
	 * @throws CrimsonLogicException Excepcion en caso de falla
	 */
	ACKEntity createAck(ACKEntity entity) throws CrimsonLogicException;

	/**
	 * Permite crear mensaje de error
	 * 
	 * @return Mensaje de error
	 * 
	 * @throws CrimsonLogicException Excepcion en caso de falla
	 */
	ErrorMesaggeEntity createError(ErrorMesaggeEntity error) throws CrimsonLogicException;

	/**
	 * Permite eliminar un Envio Fitosaitario de la BD.
	 * 
	 * @param id Identificador del envio fitasinatario a eliminar.
	 * 
	 * @throws CrimsonLogicException Excepcion en caso de falla
	 */
	void deleteAck(Long id) throws CrimsonLogicException;

}
