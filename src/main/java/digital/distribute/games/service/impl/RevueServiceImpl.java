package digital.distribute.games.service.impl;

import digital.distribute.games.dao.RevueDAO;
import digital.distribute.games.entity.Revue;
import digital.distribute.games.service.RevueService;

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
public class RevueServiceImpl implements RevueService {

    private final Logger logger;

    private final RevueDAO revueDAO;

    @Inject
    public RevueServiceImpl(Logger logger, RevueDAO revueDAO) {
        this.logger = logger;
        this.revueDAO = revueDAO;
    }

    @Override
    public Revue get(Long id) {
        final Revue revue = revueDAO.find(id);
        logger.info("Returning revue: " + revue);
        return revue;
    }

    @Override
    public void create(Revue revue) {
        revueDAO.save(revue);
        logger.info("Created new revue with id: " + revue.getId());
    }

    @Override
    public void delete(Long id) {
        delete(revueDAO.find(id));
    }

    @Override
    public void delete(Revue revue) {
        revueDAO.delete(revue);
        logger.info("Revue with id " + revue.getId() + " deleted.");
    }

    @Override
    public void update(Revue revue) {
        revueDAO.update(revue);
        logger.info("Updated revue with id " + revue.getId());
    }

    @Override
    public List<Revue> findAll() {
        List<Revue> revues = revueDAO.findAll();
        logger.info("Returning " + revues.size() + " revues");
        return revues;
    }

    @Override
    public List<Revue> findByGameID(Long gameID) {
        List<Revue> revues = new LinkedList();
        revues.addAll(revueDAO.findByProperty("game", gameID));
        logger.info("Returning " + revues.size() + " revues.");
        return revues;
    }

    @Override
    public List<Revue> findByUserID(Long userID) {
        List<Revue> revues = new LinkedList();
        revues.addAll(revueDAO.findByProperty("player", userID));
        logger.info("Returning " + revues.size() + " revues.");
        return revues;
    }
}
