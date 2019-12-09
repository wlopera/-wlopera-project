package org.wlopera.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wlopera.project.entity.ACKEntity;

@Repository
@Transactional
public interface ACKEntityRepository extends JpaRepository<ACKEntity, Long> {

}
