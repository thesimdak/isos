package cz.svetsplhu.isos.repository.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entity for persisting a category of a competitors (e.g. men, juniors etc.)
 */
@Entity(name="category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity category = (CategoryEntity) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
