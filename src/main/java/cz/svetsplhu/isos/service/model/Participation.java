package cz.svetsplhu.isos.service.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Model for a participation on a competition.
 */
public class Participation {

    private Long id;
    private RopeClimber ropeClimber;
    private Competition competition;
    private List<Time> timeList = new ArrayList<>();
    private Category category;
    private String organization;
    private int resultRank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RopeClimber getRopeClimber() {
        return ropeClimber;
    }

    public void setRopeClimber(RopeClimber ropeClimber) {
        this.ropeClimber = ropeClimber;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<Time> getTimeList() {
        return timeList;
    }

    public void setResultRank(int resultRank) {
        this.resultRank = resultRank;
    }

    public int getResultRank() {
        return resultRank;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "id=" + id +
                ", ropeClimber=" + ropeClimber +
                ", competition=" + competition +
                ", timeList=" + timeList +
                ", category=" + category +
                ", organization='" + organization + '\'' +
                ", resultRank=" + resultRank +
                '}';
    }
}
