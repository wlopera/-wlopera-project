package org.wlopera.project.service.impl;

import java.math.BigDecimal;
import java.util.Map;
import java.util.WeakHashMap;

import javax.xml.ws.BindingProvider;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.ext.logging.LoggingInInterceptor;
import org.apache.cxf.ext.logging.LoggingOutInterceptor;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.wss4j.dom.WSConstants;
import org.apache.wss4j.dom.handler.WSHandlerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wlopera.project.mapper.CrimsonLogicServiceMapper;
import org.wlopera.project.model.AcknowledgmentReceiptDTO;
import org.wlopera.project.model.SignedInfoDTO;
import org.wlopera.project.service.CrimsonLogicService;
import org.wlopera.project.service.generated.crimsonlogic.client.EnvioCertificadoFitosanitarioEndPoint;
import org.wlopera.project.service.generated.crimsonlogic.client.EnvioCertificadoFitosanitarioService;
import org.wlopera.project.service.generated.crimsonlogic.client.MensajeFalla;
import org.wlopera.project.service.generated.crimsonlogic.model.EnvioCertificadoFitosanitarioType;
import org.wlopera.project.service.security.CrimsonLogicPasswordCallBack;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CrimsonLogicServiceImpl implements CrimsonLogicService {

	@Value("${config.wsdl.endpoint}")
	private String endpoint;

	@Value("${config.wsdl.user}")
	private String user;

	@Value("${config.wsdl.password}")
	private String password;

	@Autowired
	private CrimsonLogicServiceMapper mapper;

	@Override
	public AcknowledgmentReceiptDTO sendCertificateOperation(final SignedInfoDTO info) {
		AcknowledgmentReceiptDTO response = null;

		try {
			final EnvioCertificadoFitosanitarioEndPoint port = new EnvioCertificadoFitosanitarioService()
					.getPort(EnvioCertificadoFitosanitarioEndPoint.class);
			final BindingProvider provider = (BindingProvider) port;
			final Client client = org.apache.cxf.frontend.ClientProxy.getClient(port);
			
			final Endpoint cxfEndpoint = client.getEndpoint();
			cxfEndpoint.getInInterceptors().add(new LoggingInInterceptor());
			cxfEndpoint.getOutInterceptors().add(new LoggingOutInterceptor());

			provider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);

			final Map<String, Object> outProps = new WeakHashMap<>();
			outProps.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
			outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
			outProps.put(WSHandlerConstants.PW_CALLBACK_REF, new CrimsonLogicPasswordCallBack(user, password));
			outProps.put(WSHandlerConstants.USER, user);

			final WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);
			cxfEndpoint.getOutInterceptors().add(wssOut);

			final EnvioCertificadoFitosanitarioType envioCertificado = mapper.signedInfoToService(info);

			if (null != envioCertificado) {
				envioCertificado.setVersionEsquema(new BigDecimal("0.4"));

				if (null != envioCertificado.getCabecera()) {
					envioCertificado.getCabecera().setVersionEsquema(new BigDecimal("0.2"));
				}
			}

			response = mapper.acuseReciboToAcknowloged(port.envioCertificadoFitosanitarioOperation(envioCertificado));
		} catch (MensajeFalla e) {
			log.error("Se ha presentado el siguiente error", e);
			response = mapper.mensajeFallaToAcknowloged(e.getFaultInfo());
		}

		return response;
	}
}
