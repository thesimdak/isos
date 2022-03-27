package cz.svetsplhu.isos.repository;

import cz.svetsplhu.isos.repository.model.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository for manipulation with a competition in the DB.
 */
public interface CompetitionRepository extends JpaRepository<CompetitionEntity, Long> {
    CompetitionEntity findByNameAndCompetitionNameAndDate(String name, String competitionName, LocalDate date);

    @Query("select c from competition c where YEAR(c.date) = :year order by c.date desc")
    List<CompetitionEntity> findByYear(@Param("year") Integer year);

    @Query("select c from competition c order by c.date desc")
    List<CompetitionEntity> findAllByOrderByDateDesc();
}
