package cz.svetsplhu.isos.service.model;

import java.math.BigDecimal;

/**
 * REST model for best time on a competition by a rope climber.
 */
public class CompetitionTime {
    private String competitionName;
    private BigDecimal time;

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public BigDecimal getTime() {
        return time;
    }

    public void setTime(BigDecimal time) {
        this.time = time;
    }
}
