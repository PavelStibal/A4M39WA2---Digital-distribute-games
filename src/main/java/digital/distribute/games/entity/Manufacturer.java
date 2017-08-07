package digital.distribute.games.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * @author Pavel Stibal
 */
@Entity
public class Manufacturer extends AbstractEntity{
    @NotNull
    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
