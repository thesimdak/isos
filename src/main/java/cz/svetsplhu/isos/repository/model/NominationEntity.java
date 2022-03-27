package cz.svetsplhu.isos.repository.model;

import java.util.Objects;

/**
 * Class for mapping result of native sql containing a nomination to a national championship.
 */
public class NominationEntity {
    private String competitionName;
    private String firstName;
    private String lastName;
    private String yearOfBirth;
    private String time;

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NominationEntity that = (NominationEntity) o;
        return Objects.equals(competitionName, that.competitionName) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(yearOfBirth, that.yearOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competitionName, firstName, lastName, yearOfBirth);
    }

    @Override
    public String toString() {
        return "Nomination{" +
                "competitionName='" + competitionName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
