package org.wlopera.project.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.wlopera.project.model.AcknowledgmentReceiptDTO;
import org.wlopera.project.model.DetailErrorDTO;
import org.wlopera.project.model.HeadDTO;
import org.wlopera.project.model.SignedInfoDTO;
import org.wlopera.project.service.generated.crimsonlogic.model.AcuseRecibo;
import org.wlopera.project.service.generated.crimsonlogic.model.CabeceraType;
import org.wlopera.project.service.generated.crimsonlogic.model.DetallesErrorType;
import org.wlopera.project.service.generated.crimsonlogic.model.EnvioCertificadoFitosanitarioType;
import org.wlopera.project.service.generated.crimsonlogic.model.MensajeFallaType;

@Mapper()
public interface CrimsonLogicServiceMapper {

	@Mapping(target = "cabecera", source = "header")
	@Mapping(target = "signedSPSCert", source = "psCert")
	EnvioCertificadoFitosanitarioType signedInfoToService(final SignedInfoDTO signed);

	@Mapping(target = "codigoInteraccion", source = "iteractionCode")
	@Mapping(target = "estadoTransaccion", source = "transactionStatus")
	@Mapping(target = "fechaHora", source = "date")
	@Mapping(target = "IDdeDestinatario", source = "receiverId")
	@Mapping(target = "IDdeDocumento", source = "documentId")
	@Mapping(target = "IDdeRemitente", source = "senderId")
	@Mapping(target = "IDdeServicio", source = "serviceId")
	@Mapping(target = "IDdeTransaccionOriginal", source = "transactionOriginalId")
	@Mapping(target = "IDdeTransaccion", source = "transactionId")
	@Mapping(target = "paisdeDestinatario", source = "receiverCountry")
	@Mapping(target = "paisdeRemitente", source = "senderCountry")
	@Mapping(target = "tipodeDocumento", source = "documentType")
	CabeceraType headerToCabeceraType(final HeadDTO header);

	@Mapping(target = "head", source = "cabecera")
	@Mapping(target = "date", source = "fechaHoraAcuseRecibo")
	@Mapping(target = "code", source = "codigoAcuseRecibo")
	@Mapping(target = "message", source = "glosaAcuseRecibo")
	AcknowledgmentReceiptDTO acuseReciboToAcknowloged(final AcuseRecibo acuse);

	@Mapping(target = "head", source = "cabecera")
	@Mapping(target = "errorInfo.details", source = "detallesError")
	AcknowledgmentReceiptDTO mensajeFallaToAcknowloged(final MensajeFallaType mensajeFalla);

	@IterableMapping(elementTargetType = DetailErrorDTO.class)
	List<DetailErrorDTO> detallesErrorToDetails(final List<DetallesErrorType> detalles);

	@Mapping(target = "code", source = "codigodeError")
	@Mapping(target = "message", source = "mensajedeError")
	DetailErrorDTO detalleErrorToDetail(final DetallesErrorType detalle);
}
