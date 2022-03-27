package cz.svetsplhu.isos.repository.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import cz.svetsplhu.isos.rest.model.serializer.ParticipationSerializer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Entity for persisting a participation on a competition.
 */
@Entity(name="participation")
@JsonSerialize(using = ParticipationSerializer.class)
public class ParticipationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY)
    private RopeClimberEntity ropeClimber;

    @ManyToOne(fetch= FetchType.LAZY)
    private CompetitionEntity competition;

    @OneToMany(fetch= FetchType.LAZY)
    @OrderBy(value = "round")
    private List<TimeEntity> timeList = new ArrayList<>();

    @ManyToOne(fetch= FetchType.LAZY)
    private CategoryEntity category;

    private String organization;

    @Transient
    private List<Double> timeDoubleList = new ArrayList<>();

    @Transient
    private int resultRank;

    public ParticipationEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RopeClimberEntity getRopeClimber() {
        return ropeClimber;
    }

    public void setRopeClimber(RopeClimberEntity ropeClimber) {
        this.ropeClimber = ropeClimber;
    }

    public CompetitionEntity getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionEntity competition) {
        this.competition = competition;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public List<TimeEntity> getTimeList() {
        return timeList;
    }

    public void setResultRank(int resultRank) {
        this.resultRank = resultRank;
    }

    public int getResultRank() {
        return resultRank;
    }

    @Transient
    public Double getTopTime() {
        Double topTime = 999D;
        for (TimeEntity time : timeList) {
            if (time.getTime() < topTime) {
                topTime = time.getTime();
            }
        }
        return topTime;
    }

    public List<Double> getTimeDoubleList() {
        if (this.timeDoubleList == null || this.timeDoubleList.isEmpty()) {
            this.timeDoubleList = timeList.stream().map(TimeEntity::getTime).collect(Collectors.toList());
            Collections.sort(timeDoubleList);
        }
        return this.timeDoubleList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParticipationEntity that = (ParticipationEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
                ", timeDoubleList=" + timeDoubleList +
                ", resultRank=" + resultRank +
                '}';
    }
}
