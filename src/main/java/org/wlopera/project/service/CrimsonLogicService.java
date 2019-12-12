package org.wlopera.project.service;

import org.wlopera.project.model.AcknowledgmentReceiptDTO;
import org.wlopera.project.model.SignedInfoDTO;

/**
 * Permite la consulta de los envio de certificados fitosanitarios al Wen Server - Soap.
 * 
 * @author William Lopera
 *
 */
public interface CrimsonLogicService {

	AcknowledgmentReceiptDTO sendCertificateOperation(final SignedInfoDTO info);

}
