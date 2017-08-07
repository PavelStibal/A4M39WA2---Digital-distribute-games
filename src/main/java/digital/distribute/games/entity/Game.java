package digital.distribute.games.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Pavel Stibal
 */
@Entity
public class Game extends AbstractStringEntity{
    @Column(columnDefinition = "text")
    private String parameters;

    @ManyToOne
    @Cascade(value = {CascadeType.DELETE, CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "category_id", columnDefinition = "int4 NOT NULL")
    private Category category;

    @ManyToOne
    @Cascade(value = {CascadeType.DELETE, CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "manufac_id", columnDefinition = "int4 NOT NULL")
    private Manufacturer manufacturer;

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
