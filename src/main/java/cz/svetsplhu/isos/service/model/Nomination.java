package cz.svetsplhu.isos.service.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Model of a nomination to a national championship.
 */
public class Nomination {
    private String firstName;
    private String lastName;
    private String yearOfBirth;
    private BigDecimal time;
    private List<CompetitionTime> competitionTimes;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public BigDecimal getTime() {
        return time;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
    }

    public List<CompetitionTime> getCompetitionTimes() {
        if (competitionTimes == null) {
            competitionTimes = new ArrayList<>();
        }
        return competitionTimes;
    }

    public void setCompetitionTimes(List<CompetitionTime> competitionTimes) {
        this.competitionTimes = competitionTimes;
    }

    @Override
    public String toString() {
        return "NominationDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", time='" + time + '\'' +
                ", competitionTimes=" + competitionTimes +
                '}';
    }
}
