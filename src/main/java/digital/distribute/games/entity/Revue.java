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
public class Revue extends AbstractStringEntity {
    @ManyToOne
    @Cascade(value = {CascadeType.DELETE, CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "player_id", columnDefinition = "int4 NOT NULL")
    private Player player;

    @ManyToOne
    @Cascade(value = {CascadeType.DELETE, CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "game_id", columnDefinition = "int4 NOT NULL")
    private Game game;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
