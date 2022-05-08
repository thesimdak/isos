package cz.svetsplhu.isos.rest.model;

public class NominationCriteriaDto {
    private Long id;
    private Long categoryId;
    private Integer year;
    private Double timeCriteria;
    private Integer minParticipationCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getTimeCriteria() {
        return timeCriteria;
    }

    public void setTimeCriteria(Double timeCriteria) {
        this.timeCriteria = timeCriteria;
    }

    public Integer getMinParticipationCount() {
        return minParticipationCount;
    }

    public void setMinParticipationCount(Integer minParticipationCount) {
        this.minParticipationCount = minParticipationCount;
    }

    @Override
    public String toString() {
        return "NominationCriteriaDto{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", year=" + year +
                ", timeCriteria=" + timeCriteria +
                ", minParticipationCount=" + minParticipationCount +
                '}';
    }
}
