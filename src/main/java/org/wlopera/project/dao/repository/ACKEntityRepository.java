package org.wlopera.project.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.wlopera.project.dao.entity.AckEntity;

@Repository
@Transactional
public interface ACKEntityRepository extends JpaRepository<AckEntity, Long> {

}
