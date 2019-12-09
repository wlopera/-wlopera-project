package org.wlopera.project.service;

import org.wlopera.project.model.AcknowledgmentReceiptDTO;
import org.wlopera.project.model.SignedInfoDTO;

public interface CrimsonLogicService {

	AcknowledgmentReceiptDTO sendCertificateOperation(final SignedInfoDTO info);

}
