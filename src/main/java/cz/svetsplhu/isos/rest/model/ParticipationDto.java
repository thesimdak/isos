package cz.svetsplhu.isos.rest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.svetsplhu.isos.rest.model.serializer.ParticipationSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * REST model a participation on a competition.
 */
@JsonSerialize(using = ParticipationSerializer.class)
public class ParticipationDto {

    private Long id;
    private RopeClimberDto ropeClimber;
    private CompetitionDto competition;
    private List<TimeDto> timeList = new ArrayList<>();
    private CategoryDto category;
    private String organization;
    private int resultRank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RopeClimberDto getRopeClimber() {
        return ropeClimber;
    }

    public void setRopeClimber(RopeClimberDto ropeClimber) {
        this.ropeClimber = ropeClimber;
    }

    public CompetitionDto getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionDto competition) {
        this.competition = competition;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<TimeDto> getTimeList() {
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
