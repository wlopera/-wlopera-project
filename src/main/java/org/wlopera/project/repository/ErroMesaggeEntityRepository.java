package org.wlopera.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wlopera.project.entity.ErrorMesaggeEntity;

@Repository
@Transactional
public interface ErroMesaggeEntityRepository extends JpaRepository<ErrorMesaggeEntity, Long> {

}
