package cz.svetsplhu.isos.repository.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Class for mapping result of native sql containing a nomination to a national championship.
 */
public class NominationEntity {
    private String competitionName;
    private LocalDate date;
    private String firstName;
    private String lastName;
    private String yearOfBirth;
    private BigDecimal time;

    public NominationEntity() {
    }

    public NominationEntity(String competitionName, LocalDate date, String firstName, String lastName, String yearOfBirth, BigDecimal time) {
        this.competitionName = competitionName;
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
        this.time = time;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public BigDecimal getTime() {
        return time;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NominationEntity that = (NominationEntity) o;
        return Objects.equals(competitionName, that.competitionName) && Objects.equals(date, that.date) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(yearOfBirth, that.yearOfBirth) && Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competitionName, date, firstName, lastName, yearOfBirth, time);
    }

    @Override
    public String toString() {
        return "NominationEntity{" +
                "competitionName='" + competitionName + '\'' +
                ", date=" + date +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
