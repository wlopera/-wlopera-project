package org.wlopera.project.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wlopera.project.entity.ACKEntity;
import org.wlopera.project.entity.ErrorMesaggeEntity;
import org.wlopera.project.exception.CrimsonLogicException;
import org.wlopera.project.repository.ACKEntityRepository;
import org.wlopera.project.repository.ErroMesaggeEntityRepository;
import org.wlopera.project.util.CrimsonLogicMessageErrorEnum;

@Service
public class CrimsonLoginDtoService implements CrimsonLoginDtoApi {

	@Autowired
	ACKEntityRepository ackRepository;
	
	@Autowired
	ErroMesaggeEntityRepository errorRepository;

	@Override
	public List<ACKEntity> getAcks() throws CrimsonLogicException {

		try {

			List<ACKEntity> outputList = ackRepository.findAll();

			if (CollectionUtils.isNotEmpty(outputList)) {
				return outputList;
			}

		} catch (Exception e) {

			throw new CrimsonLogicException(CrimsonLogicMessageErrorEnum.NO_EXIT_REGISTER_BY_GET_ALL.getId());
		}

		return new ArrayList<>();

	}

	@Override
	public ACKEntity getAckById(Long id) throws CrimsonLogicException {

		Optional<ACKEntity> output = ackRepository.findById(id);

		if (output.isPresent()) {
			return output.get();
		}

		throw new CrimsonLogicException(CrimsonLogicMessageErrorEnum.NO_EXIT_REGISTER_BY_ID.getId() + id);

	}

	// TODO: Se puede ajustar el codigo para actualizar si el registro ya existe
	@Override
	public ACKEntity createAck(ACKEntity entity) throws CrimsonLogicException {

		try {

			entity = ackRepository.save(entity);

		} catch (Exception e) {

			throw new CrimsonLogicException(CrimsonLogicMessageErrorEnum.ERROR_CREATE_REGISTER.getId());
		}

		return entity;

	}
	
	// TODO: Se puede ajustar el codigo para actualizar si el registro ya existe
	@Override
	public ErrorMesaggeEntity createError(ErrorMesaggeEntity error) throws CrimsonLogicException{

		try {

			error = errorRepository.save(error);

		} catch (Exception e) {

			throw new CrimsonLogicException(CrimsonLogicMessageErrorEnum.ERROR_CREATE_REGISTER.getId());
		}

		return error;

	}

	@Override
	public void deleteAck(Long id) throws CrimsonLogicException {

		Optional<ACKEntity> output = ackRepository.findById(id);

		if (output.isPresent()) {
			ackRepository.deleteById(id);
			return;
		}

		throw new CrimsonLogicException(CrimsonLogicMessageErrorEnum.ERROR_DELETE_REGISTER.getId() + id);

	}

}
