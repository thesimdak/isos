package cz.svetsplhu.isos.repository;

import cz.svetsplhu.isos.repository.model.TimeEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for manipulation with a time of a rope climber on a competition in the DB.
 */
public interface TimeRepository extends CrudRepository<TimeEntity, Long> {
}