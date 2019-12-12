package org.wlopera.project.dao.api.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlopera.project.dao.api.CrimsonLoginDAO;
import org.wlopera.project.dao.entity.AckEntity;
import org.wlopera.project.dao.mapper.CrimsonLogicDAOMapper;
import org.wlopera.project.dao.repository.ACKEntityRepository;
import org.wlopera.project.dao.repository.ErroMesaggeEntityRepository;
import org.wlopera.project.exception.CrimsonLogicException;
import org.wlopera.project.util.CrimsonLogicMessageErrorEnum;
import org.wlopera.project.web.model.AckDTO;
import org.wlopera.project.web.model.ErrorMesaggeDTO;

@Service
public class CrimsonLoginDAOImpl implements CrimsonLoginDAO {

	@Autowired
	ACKEntityRepository ackRepository;

	@Autowired
	ErroMesaggeEntityRepository errorRepository;

	@Autowired
	private CrimsonLogicDAOMapper mapper;

	@Override
	public List<AckDTO> getAcks() throws CrimsonLogicException {

		try {

			List<AckDTO> outputList = mapper.ackDtoToAckEntity(ackRepository.findAll());

			if (CollectionUtils.isNotEmpty(outputList)) {
				return outputList;
			}

		} catch (Exception e) {

			throw new CrimsonLogicException(CrimsonLogicMessageErrorEnum.NO_EXIT_REGISTER_BY_GET_ALL.getId());
		}

		return new ArrayList<>();

	}

	@Override
	public AckDTO getAckById(Long id) throws CrimsonLogicException {

		Optional<AckEntity> output = ackRepository.findById(id);

		if (output.isPresent()) {
			return mapper.ackEntityToAckDto(output.get());
		}

		throw new CrimsonLogicException(CrimsonLogicMessageErrorEnum.NO_EXIT_REGISTER_BY_ID.getId() + id);

	}

	// TODO: Se puede ajustar el codigo para actualizar si el registro ya existe
	@Override
	public AckDTO createAck(AckDTO ackDto) throws CrimsonLogicException {

		try {

			return mapper.ackEntityToAckDto(ackRepository.save(mapper.ackDtoToAckEntity(ackDto)));

		} catch (Exception e) {

			throw new CrimsonLogicException(CrimsonLogicMessageErrorEnum.ERROR_CREATE_REGISTER.getId());
		}

	}

	// TODO: Se puede ajustar el codigo para actualizar si el registro ya existe
	@Override
	public ErrorMesaggeDTO createError(ErrorMesaggeDTO error, Long ackId) throws CrimsonLogicException {

		try {

			error = mapper.errorEntityToErrorDTO(errorRepository.save(mapper.errorDtoToErrorEntity(error, ackId)));

		} catch (Exception e) {

			throw new CrimsonLogicException(CrimsonLogicMessageErrorEnum.ERROR_CREATE_REGISTER.getId());
		}

		return error;

	}

	@Override
	public void deleteAck(Long id) throws CrimsonLogicException {

		Optional<AckEntity> output = ackRepository.findById(id);

		if (output.isPresent()) {
			ackRepository.deleteById(id);
			return;
		}

		throw new CrimsonLogicException(CrimsonLogicMessageErrorEnum.ERROR_DELETE_REGISTER.getId() + id);

	}

}
