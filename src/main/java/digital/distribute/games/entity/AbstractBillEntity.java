package digital.distribute.games.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Pavel Stibal
 */
@MappedSuperclass
public class AbstractBillEntity extends AbstractEntity {
    @NotNull
    private Date date;

    @NotNull
    @Column(columnDefinition = "text")
    private String description;

    @NotNull
    private Double cost;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
