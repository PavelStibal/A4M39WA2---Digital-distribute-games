package digital.distribute.games.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Pavel Stibal
 */
@Entity
public class BillingAcc extends AbstractBillEntity {

    @ManyToOne
    @Cascade(value = {CascadeType.DELETE, CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "player_id", columnDefinition = "int4 NOT NULL")
    private Player player;

    public Player getPlayer_id() {
        return player;
    }

    public void setPlayer_id(Player player) {
        this.player = player;
    }
}
