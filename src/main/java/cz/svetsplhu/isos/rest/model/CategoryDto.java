package cz.svetsplhu.isos.rest.model;

/**
 * REST model for a category of a competitors (e.g. men, juniors etc.)
 */
public class CategoryDto {

    private Long id;
    private String categoryKey;
    private String label;
    private Double ropeLength;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getRopeLength() {
        return ropeLength;
    }

    public void setRopeLength(Double ropeLength) {
        this.ropeLength = ropeLength;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryKey='" + categoryKey + '\'' +
                ", label='" + label + '\'' +
                ", ropeLength=" + ropeLength +
                '}';
    }
}
