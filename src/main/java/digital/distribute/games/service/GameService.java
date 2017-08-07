package digital.distribute.games.service;

import digital.distribute.games.entity.Game;

import java.util.List;

/**
 * @author Pavel Stibal
 */
public interface GameService extends AbstractService<Game> {
    List<Game> findByManufacturerID(Long manufacID);
    List<Game> findByCategoryID(Long categotyID);
}
