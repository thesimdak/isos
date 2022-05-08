package cz.svetsplhu.isos.rest.model;

import java.util.List;

/**
 * REST model for nominations.
 */
public class NominationContainerDto {

    private List<CompetitionDto> competitionList;
    private List<NominationDto> nominationList;
    private NominationCriteriaDto nominationCriteria;

    public NominationContainerDto() {
    }

    public NominationContainerDto(List<CompetitionDto> competitionList, List<NominationDto> nominationList) {
        this.competitionList = competitionList;
        this.nominationList = nominationList;
    }

    public List<CompetitionDto> getCompetitionList() {
        return competitionList;
    }

    public void setCompetitionList(List<CompetitionDto> competitionList) {
        this.competitionList = competitionList;
    }

    public List<NominationDto> getNominationList() {
        return nominationList;
    }

    public void setNominationList(List<NominationDto> nominationList) {
        this.nominationList = nominationList;
    }

    @Override
    public String toString() {
        return "NominationContainerDto{" +
                "competitionList=" + competitionList +
                ", nominationList=" + nominationList +
                '}';
    }
}
