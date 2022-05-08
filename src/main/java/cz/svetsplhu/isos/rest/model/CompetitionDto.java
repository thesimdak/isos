package cz.svetsplhu.isos.rest.model;

import java.time.LocalDate;

/**
 * REST model for a competition.
 */
public class CompetitionDto {

    private Long id;
    private String name;
    private String competitionName;
    private LocalDate date;
    private String place;
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


    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
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
