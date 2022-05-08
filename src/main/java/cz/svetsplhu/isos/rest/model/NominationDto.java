package cz.svetsplhu.isos.rest.model;

import java.util.List;

/**
 * Rest model for a nomination to a national championship.
 */
public class NominationDto {

    private String firstName;
    private String lastName;
    private String yearOfBirth;
    private String time;
    private List<CompetitionTimeDto> competitionTimes;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<CompetitionTimeDto> getCompetitionTimes() {
        return competitionTimes;
    }

    public void setCompetitionTimes(List<CompetitionTimeDto> competitionTimes) {
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
