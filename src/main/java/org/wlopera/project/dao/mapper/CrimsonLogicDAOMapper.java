package org.wlopera.project.dao.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.wlopera.project.dao.entity.AckEntity;
import org.wlopera.project.dao.entity.ErrorMesaggeEntity;
import org.wlopera.project.web.model.AckDTO;
import org.wlopera.project.web.model.ErrorMesaggeDTO;

public class CrimsonLogicDAOMapper {

	/**
	 * Conversion de objeto de BD a modelo de negocio.
	 * 
	 * @param input Objeto 'AckEntity' de BD
	 * 
	 * @return Objeto 'AckDTO' de negocio
	 */
	public AckDTO ackEntityToAckDto(AckEntity input) {

		AckDTO output = new AckDTO();

		output.setId(input.getAckId());
		output.setTransaccionId(input.getTransaccionId());
		output.setCode(input.getCode());
		output.setMessage(input.getMessage());
		output.setDate(input.getDate());

		if (CollectionUtils.isNotEmpty(input.getMensajesError())) {

			Set<ErrorMesaggeDTO> errores = new HashSet<>();

			for (ErrorMesaggeEntity auxError : input.getMensajesError()) {
				errores.add(errorEntityToErrorDTO(auxError));
			}

			output.setMensajesError(errores);
		}

		return output;

	}

	/**
	 * Conversion de modelo negocio a modelo BD.
	 * 
	 * @param input Ojeto 'AckDTO' de negocio
	 * 
	 * @return Objeto 'AckEntity' de BD
	 */
	public AckEntity ackDtoToAckEntity(AckDTO input) {

		AckEntity output = new AckEntity();

		output.setAckId(input.getId());
		output.setTransaccionId(input.getTransaccionId());
		output.setCode(input.getCode());
		output.setMessage(input.getMessage());
		output.setDate(input.getDate());

		if (CollectionUtils.isNotEmpty(input.getMensajesError())) {

			Set<ErrorMesaggeEntity> errores = new HashSet<>();

			for (ErrorMesaggeDTO auxError : input.getMensajesError()) {
				errores.add(errorDtoToErrorEntity(auxError, output.getAckId()));
			}
			
			output.setMensajesError(errores);

		}

		return output;

	}

	/**
	 * Conversion de lista de objetos modelo de BD a modelo de negocio.
	 * 
	 * @param inputList Lista de objetos 'AckEntity' de BD.
	 * 
	 * @return Lista de objetos 'AckDTO' de negocio
	 */
	public List<AckDTO> ackDtoToAckEntity(List<AckEntity> inputList) {

		List<AckDTO> outputList = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(inputList)) {

			for (AckEntity input : inputList) {
				outputList.add(ackEntityToAckDto(input));
			}

		}

		return outputList;

	}

	/**
	 * Conversion de lista de objetos modelo negocio a modelo BD.
	 * 
	 * @param inputList Lista de objetos 'AckDTO' de negocio
	 * 
	 * @return Lista de objetos 'AckEntity' de BD
	 */
	public List<AckEntity> ackEntityToAckDto(List<AckDTO> inputList) {

		List<AckEntity> outputList = new ArrayList<>();

		if (CollectionUtils.isNotEmpty(inputList)) {

			for (AckDTO input : inputList) {
				outputList.add(ackDtoToAckEntity(input));
			}

		}

		return outputList;

	}

	/**
	 * Conversion de objeto de error del modelo de BD a modelo de negocio.
	 * 
	 * @param input Objeto de error 'ErrorMesaggeEntity' de BD.
	 * 
	 * @return Objeto de error 'ErrorMesaggeDTO' del negocio
	 */
	public ErrorMesaggeDTO errorEntityToErrorDTO(ErrorMesaggeEntity input) {

		ErrorMesaggeDTO output = new ErrorMesaggeDTO();

		output.setId(input.getErrorId());
		output.setCode(input.getCode());
		output.setMessage(input.getMessage());

		return output;

	}

	/**
	 * Conversion de objeto de error del modelo de negocio a modelo de BD.
	 * 
	 * @param input Objeto de error 'ErrorMesaggeDTO' de negocio
	 * 
	 * @return Objeto de error 'ErrorMesaggeEntity' de BD
	 */
	public ErrorMesaggeEntity errorDtoToErrorEntity(ErrorMesaggeDTO input, Long ackId) {

		ErrorMesaggeEntity output = new ErrorMesaggeEntity();

		output.setErrorId(input.getId());
		output.setAckId(ackId);
		output.setCode(input.getCode());
		output.setMessage(input.getMessage());

		return output;

	}

}
