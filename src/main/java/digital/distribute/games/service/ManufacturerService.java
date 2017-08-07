package digital.distribute.games.service;

import digital.distribute.games.entity.Manufacturer;

import java.util.List;

/**
 * @author Pavel Stibal
 */
public interface ManufacturerService extends AbstractService<Manufacturer> {
    List<Manufacturer> findByName(String name);
}
