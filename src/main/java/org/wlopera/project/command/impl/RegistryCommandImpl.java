package org.wlopera.project.command.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.wlopera.project.api.CrimsonLoginDtoApi;
import org.wlopera.project.command.RegistryCommand;
import org.wlopera.project.entity.ACKEntity;
import org.wlopera.project.entity.ErrorMesaggeEntity;
import org.wlopera.project.model.AcknowledgmentReceiptDTO;
import org.wlopera.project.model.DetailErrorDTO;
import org.wlopera.project.model.HeadDTO;
import org.wlopera.project.model.SignedInfoDTO;
import org.wlopera.project.service.CrimsonLogicService;
import org.wlopera.project.web.model.RegistryDTO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RegistryCommandImpl implements RegistryCommand {

	@Autowired
	private CrimsonLogicService service;

	@Autowired
	CrimsonLoginDtoApi api;

	@Override
	public boolean execute(final RegistryDTO registry, final Model model) {
		boolean success = true;

		if (null != registry) {
			final HeadDTO head = new HeadDTO();
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

			final SignedInfoDTO signed = new SignedInfoDTO();
			signed.setHeader(head);
			signed.setPsCert(registry.getCertificate());

			final AcknowledgmentReceiptDTO acknowledgment = service.sendCertificateOperation(signed);

			if (null != acknowledgment) {

				if (null == acknowledgment.getErrorInfo()) {
					log.info("Acuse de recibo: " + acknowledgment);
					log.info("El llamado al servicio culmino de manera exitosa, se procede a actualizar la BD");

					try {

						ACKEntity entity = new ACKEntity();
						entity.setTransaccionId(head.getTransactionId());
						entity.setCode(acknowledgment.getCode());
						entity.setMessage(acknowledgment.getMessage());
						entity.setDate(acknowledgment.getDate());

						api.createAck(entity);
					} catch (Exception e) {
						log.error("Error guardando el acuse de recibo en base de datos: " + e.getMessage());
					}

				} else {
					success = false;
					log.info("Mensaje Fallido: " + acknowledgment.getErrorInfo().getDetails());
					model.addAttribute("details", acknowledgment.getErrorInfo().getDetails());

					try {

						ACKEntity entity = new ACKEntity();
						entity.setTransaccionId(head.getTransactionId());
						entity.setCode("ERR");

						// Cear el acuse de recibo
						entity = api.createAck(entity);
						
						ErrorMesaggeEntity error = null;

						for (DetailErrorDTO detail : acknowledgment.getErrorInfo().getDetails()) {

							error = new ErrorMesaggeEntity();
							error.setCode("ERR");
							error.setAckId(entity.getAckId());
							error.setMessage(detail.getMessage());
							
							api.createError(error);
						}
						
					} catch (Exception e) {
						log.error("Error guardando mensajes de error en base de datos: " + e.getMessage());

					}

				}

			} else {
				log.error("No se obtuvo respuesta aceptable del servidor");
				success = false;
			}
		}

		return success;
	}
}