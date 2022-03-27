package cz.svetsplhu.isos.service.model;

/**
 * Model for a time competed by a rope climber on a competition.
 */
public class Time {

    private Long id;
    private Integer round;
    private Double time;
    private Participation participation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Participation getParticipation() {
        return participation;
    }

    public void setParticipation(Participation participation) {
        this.participation = participation;
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", round=" + round +
                ", time=" + time +
                ", participation=" + participation +
                '}';
    }
}
