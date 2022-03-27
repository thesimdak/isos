package cz.svetsplhu.isos.repository;

import cz.svetsplhu.isos.repository.model.NominationEntity;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository for manipulation with a nomination in the DB.
 */
@Component
public class NominationRepository {

    private final static String NOMINATION_QUERY = "SELECT competitionName,\n" +
            "firstName,\n" +
            "lastName,\n" +
            "yearOfBirth,\n" +
            "time FROM competition c\n" +
            "JOIN participation p on p.competition_id = c.id\n" +
            "    JOIN rope_climber rc on rc.id = p.rope_climber_id\n" +
            "    JOIN (SELECT t.time as time, t.participation_id as participation_id FROM time t \n" +
            "          JOIN participation p2 on t.participation_id = p2.id) times\n" +
            "          on times.participation_id = p.id and times.time = (select min(time) from time t2 where t2.participation_id = p.id)\n" +
            "WHERE YEAR(date) = 2020 and type = 'VC'";
    private final EntityManager entityManager;

    @Inject
    public NominationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<NominationEntity> getNominations() {
        Query query = entityManager.createNativeQuery(NOMINATION_QUERY);
        return (List<NominationEntity>) query.getResultList();
    }

}
