package digital.distribute.games.service.impl;

import digital.distribute.games.dao.PlayerDAO;
import digital.distribute.games.entity.Player;
import digital.distribute.games.service.PlayerService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Pavel Stibal
 */
@Stateless
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final Logger logger;

    private final PlayerDAO playerDAO;

    @Inject
    public PlayerServiceImpl(Logger logger, PlayerDAO playerDAO) {
        this.logger = logger;
        this.playerDAO = playerDAO;
    }

    @Override
    public Player get(Long id) {
        final Player player = playerDAO.find(id);
        logger.info("Returning player: " + player);
        return player;
    }

    @Override
    public void create(Player player) {
        playerDAO.save(player);
        logger.info("Created new player with id: " + player.getId());
    }

    @Override
    public void delete(Long id) {
        delete(playerDAO.find(id));
    }

    @Override
    public void delete(Player player) {
        playerDAO.delete(player);
        logger.info("player with id " + player.getId() + " deleted.");
    }

    @Override
    public void update(Player player) {
        playerDAO.update(player);
        logger.info("Updated order with id " + player.getId());
    }

    @Override
    public List<Player> findAll() {
        List<Player> players = playerDAO.findAll();
        logger.info("Returning " + players.size() + " players");
        return players;
    }
}
