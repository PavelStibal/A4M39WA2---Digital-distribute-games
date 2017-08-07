package digital.distribute.games.controller;

import digital.distribute.games.entity.Game;
import digital.distribute.games.service.GameService;

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
public class GameController {
    @Inject
    private Logger logger;

    @Inject
    private GameService gameService;

    @Produces
    @Named(value = "newGame")
    private Game newGame;

    @PostConstruct
    public void initNewGame() {
        newGame = new Game();
    }

    public List<Game> getAllGames() throws IOException {
        try {
            List<Game> games = gameService.findAll();
            logger.fine("Games received.");
            return games;
        } catch (Exception e) {
            logger.severe("Error during getAllGames(): " + e.getMessage());
            throw new WebApplicationException();
        }
    }

    public List<Game> getAllGamesForCategory(final long id) throws IOException {
        try {
            List<Game> games = gameService.findByCategoryID(id);
            logger.fine("Games received.");
            return games;
        } catch (Exception e) {
            logger.severe("Error during getAllGamesForCategory(): " + e.getMessage());
            throw new WebApplicationException();
        }
    }

    public List<Game> getAllGamesForManufacturer(final long id) throws IOException {
        try {
            List<Game> games = gameService.findByManufacturerID(id);
            logger.fine("Games received.");
            return games;
        } catch (Exception e) {
            logger.severe("Error during getAllGamesForManufacturer(): " + e.getMessage());
            throw new WebApplicationException();
        }
    }

    public Game getGame(final long id) throws IOException {
        try {
            Game game = gameService.get(id);
            logger.fine("Game with id " + id + " found.");
            return game;
        } catch (Exception e) {
            logger.severe("Error during getGame() " + id + ": " + e.getMessage());
            throw new WebApplicationException();
        }
    }
}
