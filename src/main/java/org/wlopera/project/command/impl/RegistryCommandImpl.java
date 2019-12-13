package org.wlopera.project.command.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.wlopera.project.command.RegistryCommand;
import org.wlopera.project.dao.api.CrimsonLoginDAO;
import org.wlopera.project.exception.CrimsonLogicException;
import org.wlopera.project.model.AcknowledgmentReceiptDTO;
import org.wlopera.project.model.DetailErrorDTO;
import org.wlopera.project.model.HeadDTO;
import org.wlopera.project.model.SignedInfoDTO;
import org.wlopera.project.service.CrimsonLogicService;
import org.wlopera.project.util.CrimsonLogicMessageErrorEnum;
import org.wlopera.project.web.model.AckDTO;
import org.wlopera.project.web.model.ErrorMesaggeDTO;
import org.wlopera.project.web.model.RegistryDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RegistryCommandImpl implements RegistryCommand {

	@Autowired
	private CrimsonLogicService service;

	@Autowired
	CrimsonLoginDAO api;

	@Override
	public boolean execute(final RegistryDTO registry, final Model model) throws CrimsonLogicException {
		boolean success = true;

		if (null != registry) {

			validateRegistry(registry);

			try {

				final HeadDTO head = getHead(registry);

				final SignedInfoDTO signed = new SignedInfoDTO();
				signed.setHeader(head);
				signed.setPsCert(registry.getCertificate());

				final AcknowledgmentReceiptDTO acknowledgment = service.sendCertificateOperation(signed);

				if (null != acknowledgment) {

					if (null == acknowledgment.getErrorInfo()) {

						log.info("Acuse de recibo: " + acknowledgment);
						log.info("El llamado al servicio culmino de manera exitosa, se procede a actualizar la BD");

						saveACK(head, acknowledgment);

					} else {
						success = false;
						log.info("Mensaje Fallido: " + acknowledgment.getErrorInfo().getDetails());
						model.addAttribute("details", acknowledgment.getErrorInfo().getDetails());

						saveErrorMessage(head, acknowledgment);
					}

				} else {
					throw new CrimsonLogicException(CrimsonLogicMessageErrorEnum.ERROR_SERVER_UNKNOWN.getId());
				}
			} catch (Exception e) {
				throw new CrimsonLogicException(CrimsonLogicMessageErrorEnum.ERROR_SERVER.getId() + e.getMessage()
						+ " -" + e.getCause().getMessage());
			}
		}

		return success;
	}

	/**
	 * Genera el objeto cabecera 'HeadDTO' de peticion.
	 * 
	 * @param registry Objeto de registro de valores a procesar.
	 * 
	 * @return Cabecera de la peticion
	 */
	private HeadDTO getHead(RegistryDTO registry) {
		HeadDTO head = new HeadDTO();
		head.setDate(LocalDateTime.now());
		head.setServiceId("IOP.PE.FS.001");
		head.setDocumentId("302330");
		head.setDocumentType("FS");
		head.setIteractionCode("IOP.MC.101");
		head.setReceiverCountry("MX");
		head.setReceiverId("VUCEMX");
		head.setSenderCountry(registry.getCountry());
		head.setSenderId("VUCE" + registry.getCountry());
		head.setTransactionId(registry.getCountry() + registry.getDate() + registry.getSequential());
		head.setTransactionStatus("ECIOP");

		return head;

	}

	/**
	 * Guardar registro ACK en base de datos.
	 * 
	 * @param head           Cabecera del registro
	 * @param acknowledgment Respuesta del servicio, objeto 'ACK'.
	 * 
	 * @throws CrimsonLogicException En caso de error
	 */
	private void saveACK(HeadDTO head, AcknowledgmentReceiptDTO acknowledgment) throws CrimsonLogicException {
		try {

			AckDTO ack = new AckDTO();
			ack.setTransaccionId(head.getTransactionId());
			ack.setCode(acknowledgment.getCode());
			ack.setMessage(acknowledgment.getMessage());
			ack.setDate(acknowledgment.getDate());

			api.createAck(ack);

		} catch (Exception e) {
			throw new CrimsonLogicException(
					CrimsonLogicMessageErrorEnum.ERROR_SAVE_ACK_REGISTER.getId() + e.getMessage());
		}
	}

	/**
	 * Guardarregustro mesnaje de error en base de datos.
	 * 
	 * @param head           Cabecera del registro
	 * @param acknowledgment Respuesta del servicio, objeto 'ACK'.
	 * 
	 * @throws CrimsonLogicException En caso de error.
	 */
	private void saveErrorMessage(HeadDTO head, AcknowledgmentReceiptDTO acknowledgment) throws CrimsonLogicException {
		try {

			AckDTO ack = new AckDTO();
			ack.setTransaccionId(head.getTransactionId());
			ack.setCode("ERR");

			// Cear el acuse de recibo
			ack = api.createAck(ack);

			// Crear mensajes de errores
			if (null != acknowledgment.getErrorInfo()
					&& CollectionUtils.isNotEmpty(acknowledgment.getErrorInfo().getDetails())) {
				ErrorMesaggeDTO error = null;

				for (DetailErrorDTO detail : acknowledgment.getErrorInfo().getDetails()) {

					error = new ErrorMesaggeDTO();
					error.setCode("ERR");
					error.setId(ack.getId());
					error.setMessage(detail.getMessage());

					api.createError(error, ack.getId());
				}
			}

		} catch (Exception e) {
			throw new CrimsonLogicException(
					CrimsonLogicMessageErrorEnum.ERROR_SAVE_ERROR_REGISTER.getId() + e.getMessage());
		}
	}

	/**
	 * Valida el formato del registro a enviar en la peticion.
	 * 
	 * @param registry Registro a validar
	 * 
	 * @throws CrimsonLogicException En caso de error.
	 */
	private void validateRegistry(RegistryDTO registry) throws CrimsonLogicException {

		if (registry.getValidate()) {
			DateFormat sdf = new SimpleDateFormat("ddMMyy");
			sdf.setLenient(false);
			try {
				sdf.parse(registry.getDate());
			} catch (ParseException e) {
				throw new CrimsonLogicException(
						"[" + registry.getDate() + "]: " + CrimsonLogicMessageErrorEnum.ERROR_DATE_FORMAT.getId());
			}

			if (registry.getSequential().length() != 6) {
				throw new CrimsonLogicException("[" + registry.getSequential() + "]: "
						+ CrimsonLogicMessageErrorEnum.ERROR_SEQUENTIAL_SIZE.getId());
			}
		}

	}

}