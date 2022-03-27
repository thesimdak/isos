package cz.svetsplhu.isos.repository;

import cz.svetsplhu.isos.repository.model.RopeClimberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for manipulation with a rope climber in the DB.
 */
public interface RopeClimberRepository extends JpaRepository<RopeClimberEntity, Long> {
	RopeClimberEntity findByFirstNameAndLastNameAndYearOfBirth(String firstName,
			String lastName, Integer yearOfBirth);

	@Query("select p from ropeClimber r inner join r.participationList p inner join p.timeList times where p.category.id = :id"
			+ " and times.time = (select min(tl.time) from participation parts inner join parts.timeList tl where parts.id = p.id)")
	List<RopeClimberEntity> findTopByCat(@Param("id") Long id);
}
