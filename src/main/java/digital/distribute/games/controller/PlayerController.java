package digital.distribute.games.controller;

import digital.distribute.games.entity.Player;
import digital.distribute.games.service.PlayerService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Pavel Stibal
 */
@Model
public class PlayerController {
    @Inject
    private Logger logger;

    @Inject
    private PlayerService playerService;

    @Produces
    @Named(value = "newPlayer")
    private Player newPlayer;

    @PostConstruct
    public void initNewGame() {
        newPlayer = new Player();
    }

    public List<Player> getAllPlayers() throws IOException {
        try {
            List<Player> players = playerService.findAll();
            logger.fine("Players received.");
            return players;
        } catch (Exception e) {
            logger.severe("Error during getAllPlayers(): " + e.getMessage());
            throw new WebApplicationException();
        }
    }

    public Player getPlayer(final long id) throws IOException {
        try {
            Player player = playerService.get(id);
            logger.fine("Player with id " + id + " found.");
            return player;
        } catch (Exception e) {
            logger.severe("Error during getPlayer() " + id + ": " + e.getMessage());
            throw new WebApplicationException();
        }
    }
}
