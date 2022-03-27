package cz.svetsplhu.isos.repository;

import cz.svetsplhu.isos.repository.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository for manipulation with a category in the DB.
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    CategoryEntity findByCategoryKey(String key);

    @Query("select distinct cat from participation p join p.category cat where p.competition.id = :competitionId")
    List<CategoryEntity> findByCompetitionId(@Param("competitionId") Long competitionId);

    @Query("select distinct cat from participation p join p.category cat")
    List<CategoryEntity> findAllNotEmpty();
}
