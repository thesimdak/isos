package cz.svetsplhu.isos.rest.model;

import java.util.List;

/**
 * REST model for persisting a rope climber.
 */
public class RopeClimberDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer yearOfBirth;
    private List<ParticipationDto> participationList;

    public List<ParticipationDto> getParticipationList() {
        return participationList;
    }

    public void setParticipationList(List<ParticipationDto> participationList) {
        this.participationList = participationList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Integer yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return "RopeClimber{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", participationList=" + participationList +
                '}';
    }
}