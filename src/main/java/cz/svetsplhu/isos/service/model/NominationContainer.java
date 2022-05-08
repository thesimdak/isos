package cz.svetsplhu.isos.service.model;

import java.util.List;

/**
 * REST model for nominations.
 */
public class NominationContainer {

    private List<Competition> competitionList;
    private List<Nomination> nominationList;

    public List<Competition> getCompetitionList() {
        return competitionList;
    }

    public void setCompetitionList(List<Competition> competitionList) {
        this.competitionList = competitionList;
    }

    public List<Nomination> getNominationList() {
        return nominationList;
    }

    public void setNominationList(List<Nomination> nominationList) {
        this.nominationList = nominationList;
    }

    @Override
    public String toString() {
        return "NominationContainer{" +
                "competitionList=" + competitionList +
                ", nominationList=" + nominationList +
                '}';
    }
}
