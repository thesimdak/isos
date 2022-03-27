package cz.svetsplhu.isos.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Entity for persisting a competition.
 */
@Table(
        uniqueConstraints=
        @UniqueConstraint(columnNames={"name", "competitionName", "date"})
)
@Entity(name="competition")
public class CompetitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "competition", fetch= FetchType.LAZY)
    @JsonIgnore
    private List<ParticipationEntity> participationList = new ArrayList<>();

    private String name;

    private String competitionName;

    private LocalDate date;

    private String place;

    // Override name because of typo in the DB
    @Column(name = "jugde")
    private String judge;

    private String sensorInstallation;

    private String starter;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    public String getSensorInstallation() {
        return sensorInstallation;
    }

    public void setSensorInstallation(String sensorInstallation) {
        this.sensorInstallation = sensorInstallation;
    }

    public String getStarter() {
        return starter;
    }

    public void setStarter(String starter) {
        this.starter = starter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ParticipationEntity> getParticipationList() {
        return participationList;
    }

    public void setParticipationList(List<ParticipationEntity> participationList) {
        this.participationList = participationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompetitionEntity that = (CompetitionEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", participationList=" + participationList +
                ", name='" + name + '\'' +
                ", competitionName='" + competitionName + '\'' +
                ", date=" + date +
                ", place='" + place + '\'' +
                ", jugde='" + judge + '\'' +
                ", sensorInstallation='" + sensorInstallation + '\'' +
                ", starter='" + starter + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
