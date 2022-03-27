package cz.svetsplhu.isos.repository.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entity for persisting a time competed by a rope climber on a competition.
 */
@Entity(name="time")
public class TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer round;

    private Double time;

    public TimeEntity() {
    }

    public TimeEntity(Integer round, Double time) {
        this.round = round;
        this.time = time;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntity time = (TimeEntity) o;
        return Objects.equals(id, time.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", round=" + round +
                ", time=" + time +
                '}';
    }
}
