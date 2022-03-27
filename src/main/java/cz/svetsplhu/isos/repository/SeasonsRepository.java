package cz.svetsplhu.isos.repository;

import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository for reading seasons from the DB.
 */
@Service
public class SeasonsRepository {

    private final EntityManager entityManager;

    @Inject
    public SeasonsRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Integer> getSeasons() {
        Query query = entityManager.createNativeQuery("SELECT YEAR(date) FROM competition WHERE 1 group by YEAR(date) order by YEAR(date)");
        return query.getResultList();
    }
}
