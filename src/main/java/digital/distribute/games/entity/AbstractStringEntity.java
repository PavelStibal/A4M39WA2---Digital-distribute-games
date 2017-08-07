package digital.distribute.games.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * @author Pavel Stibal
 */
@MappedSuperclass
public class AbstractStringEntity extends AbstractEntity {
    @Column(columnDefinition = "text")
    private String description;

    @NotNull
    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
