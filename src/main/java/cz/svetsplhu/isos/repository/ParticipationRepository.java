package cz.svetsplhu.isos.repository;

import cz.svetsplhu.isos.repository.model.ParticipationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for manipulation with a participation on a competition in the DB.
 */
public interface ParticipationRepository extends JpaRepository<ParticipationEntity, Long> {
    @Query("select distinct p from participation p join fetch p.timeList time " +
            " join fetch p.ropeClimber ropeClimber where p.competition.id = :competitionId" +
            " and p.category.id = :categoryId group by p.id")
    List<ParticipationEntity> findByCompetitionIdAndCategoryId(@Param(value = "competitionId") Long competitionId
            , @Param(value = "categoryId") Long categoryId);

    @Query("select distinct p from participation p join fetch p.timeList time " +
            " join fetch p.ropeClimber ropeClimber where " +
            " p.category.id = :categoryId group by p.id")
    List<ParticipationEntity> findByCategoryId(@Param(value = "categoryId") Long categoryId);

}
