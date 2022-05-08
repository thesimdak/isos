package cz.svetsplhu.isos.repository;

import cz.svetsplhu.isos.repository.model.NominationEntity;
import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.LocalDateType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for manipulation with a nomination in the DB.
 */
@Component
public class NominationRepository {

    private final static String NOMINATION_QUERY =
            "SELECT competition_name,\n" +
            "date,\n" +
            "first_name,\n" +
            "last_name,\n" +
            "year_of_birth,\n" +
            "time as time FROM competition c\n" +
            "JOIN participation p on p.competition_id = c.id\n" +
            "    JOIN rope_climber rc on rc.id = p.rope_climber_id\n" +
            "    JOIN (SELECT t.time as time, t.participation_id as participation_id FROM time t \n" +
            "          JOIN participation p2 on t.participation_id = p2.id) times\n" +
            "          on times.participation_id = p.id and times.time = (select min(time) from time t2 where t2.participation_id = p.id)\n" +
            "WHERE YEAR(date) = :year and type = 'VC' and p.categoryId = :categoryId";
    private final EntityManager entityManager;

    @Inject
    public NominationRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<NominationEntity> getNominations(Integer year, Long categoryId) {
        Query query = entityManager.createNativeQuery(NOMINATION_QUERY);
        List<Object[]> nominations = query.unwrap(NativeQuery.class)
                .addScalar("competition_name", StringType.INSTANCE)
                .addScalar("first_name", StringType.INSTANCE)
                .addScalar("last_name", StringType.INSTANCE)
                .addScalar("time", BigDecimalType.INSTANCE)
                .addScalar("year_of_birth", StringType.INSTANCE)
                .addScalar("date", LocalDateType.INSTANCE)
                .list();
        query.setParameter("year", year);
        query.setParameter("categoryId", categoryId);
        List<NominationEntity> nominationEntities = new ArrayList<>();
        for(Object[] nominationResult : nominations) {
            String competitionName = (String) nominationResult[0];
            String firstName = (String) nominationResult[1];
            String lastName = (String) nominationResult[2];
            BigDecimal time = (BigDecimal) nominationResult[3];
            String yearOfBirth = (String) nominationResult[4];
            LocalDate date = (LocalDate) nominationResult[5];
            nominationEntities.add(new NominationEntity(competitionName, date, firstName, lastName, yearOfBirth, time));
        }
        return nominationEntities;
    }

}
