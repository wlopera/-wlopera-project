package org.wlopera.project.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wlopera.project.api.CrimsonLoginDtoApi;
import org.wlopera.project.command.RegistryCommand;
import org.wlopera.project.entity.ACKEntity;
import org.wlopera.project.exception.CrimsonLogicException;
import org.wlopera.project.web.model.RegistryDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class WebController {

	@Autowired
	private RegistryCommand registryCommand;

	@Autowired
	CrimsonLoginDtoApi api;

	@Value("${config.wsdl.certificate}")
	private String certificate;

	@RequestMapping(value = "/")
	public ModelAndView index() {
		log.info("Cargando la pagina de inicio");

		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		
		RegistryDTO registry = new RegistryDTO(certificate);
		registry.setCountry("PE");
		
		modelAndView.addObject("registry", registry);

		return modelAndView;
	}
	
	@RequestMapping(value="/dataBD", method = RequestMethod.GET)
    public String ackPage() {
        return "ack";
    }

	@PostMapping(value = "/save")
	public String save(@Valid RegistryDTO registry, BindingResult result, Model model) {
		log.info("Vamos a empezar el proceso de registrar");
		return registryCommand.execute(registry, model) ? "registry-success" : "registry-error";
	}

	@RequestMapping(value = "/acks", method = RequestMethod.GET)
	public ResponseEntity<List<ACKEntity>> getAcks() throws CrimsonLogicException {
		log.info("Consulyar todos los registros de base de datos");
		List<ACKEntity> outputlist = api.getAcks();
		return new ResponseEntity<>(outputlist, new HttpHeaders(), HttpStatus.OK);
	}
}
