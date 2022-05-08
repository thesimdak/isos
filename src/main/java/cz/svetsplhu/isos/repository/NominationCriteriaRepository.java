package cz.svetsplhu.isos.repository;

import cz.svetsplhu.isos.repository.model.NominationCriteriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for manipulation with a participation criteria in the DB.
 */
public interface NominationCriteriaRepository extends JpaRepository<NominationCriteriaEntity, Long> {

    List<NominationCriteriaEntity> findByYear(Integer year);


}
