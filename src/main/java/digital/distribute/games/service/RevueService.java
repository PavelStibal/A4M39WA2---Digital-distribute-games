package digital.distribute.games.service;

import digital.distribute.games.entity.Revue;

import java.util.List;

/**
 * @author Pavel Stibal
 */
public interface RevueService extends AbstractService<Revue> {
    List<Revue> findByGameID(Long gameID);
    List<Revue> findByUserID(Long userID);
}
