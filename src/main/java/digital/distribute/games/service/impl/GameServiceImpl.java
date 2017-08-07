package digital.distribute.games.service.impl;

import digital.distribute.games.dao.GameDAO;
import digital.distribute.games.entity.Game;
import digital.distribute.games.service.GameService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Pavel Stibal
 */
@Stateless
@Transactional
public class GameServiceImpl implements GameService {

    private final Logger logger;

    private final GameDAO gameDAO;

    @Inject
    public GameServiceImpl(Logger logger, GameDAO gameDAO) {
        this.logger = logger;
        this.gameDAO = gameDAO;
    }

    public Game get(Long id) {
        final Game game = gameDAO.find(id);
        logger.info("Returning game: " + game);
        return game;
    }

    public void create(Game game) {
        gameDAO.save(game);
        logger.info("Created a new game with id: " + game.getId());
    }

    public void delete(Long id) {
        delete(get(id));
    }

    public void delete(Game game) {
        gameDAO.delete(game);
        logger.info("Game with id " + game.getId() + " deleted");
    }

    public void update(Game game) {
        gameDAO.update(game);
        logger.info("Game with id " + game.getId() + " updated");
    }

    public List<Game> findAll() {
        List<Game> games = gameDAO.findAll();
        logger.info("Returning " + games.size() + " games");
        return games;
    }

    public List<Game> findByManufacturerID(Long manufacID) {
        List<Game> games = new LinkedList();
        games.addAll(gameDAO.findByProperty("manufacturer", manufacID));
        logger.info("Returning " + games.size() + " games.");
        return games;
    }

    public List<Game> findByCategoryID(Long categotyID) {
        List<Game> games = new LinkedList();
        games.addAll(gameDAO.findByProperty("category", categotyID));
        logger.info("Returning " + games.size() + " games.");
        return games;
    }
}
