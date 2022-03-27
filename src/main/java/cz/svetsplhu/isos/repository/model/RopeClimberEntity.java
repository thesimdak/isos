package cz.svetsplhu.isos.repository.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * Entity for persisting a rope climber.
 */
@Entity(name="ropeClimber")
public class RopeClimberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private Integer yearOfBirth;

    @OneToMany(mappedBy = "ropeClimber", fetch = FetchType.LAZY)
    private List<ParticipationEntity> participationList;

    public RopeClimberEntity() {

    }

    public RopeClimberEntity(String firstName, String lastName, Integer yearOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    public List<ParticipationEntity> getParticipationList() {
        return participationList;
    }

    public void setParticipationList(List<ParticipationEntity> participationList) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RopeClimberEntity that = (RopeClimberEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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