package digital.distribute.games.service.impl;

import digital.distribute.games.dao.ManufacturerDAO;
import digital.distribute.games.entity.Manufacturer;
import digital.distribute.games.service.ManufacturerService;

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
public class ManufacturerServiceImpl implements ManufacturerService {

    private final Logger logger;

    private final ManufacturerDAO manufacturerDAO;

    @Inject
    public ManufacturerServiceImpl(Logger logger, ManufacturerDAO manufacturerDAO) {
        this.logger = logger;
        this.manufacturerDAO = manufacturerDAO;
    }

    @Override
    public Manufacturer get(Long id) {
        final Manufacturer manufacturer = manufacturerDAO.find(id);
        logger.info("Returning manufacturer: " + manufacturer);
        return manufacturer;
    }

    @Override
    public void create(Manufacturer manufacturer) {
        manufacturerDAO.save(manufacturer);
        logger.info("Created new manufacturer with id: " + manufacturer.getId());
    }

    @Override
    public void delete(Long id) {
        delete(manufacturerDAO.find(id));
    }

    @Override
    public void delete(Manufacturer manufacturer) {
        manufacturerDAO.delete(manufacturer);
        logger.info("Manufacturer with id " + manufacturer.getId() + " deleted.");
    }

    @Override
    public void update(Manufacturer manufacturer) {
        manufacturerDAO.update(manufacturer);
        logger.info("Updated manufacturer with id " + manufacturer.getId());
    }

    @Override
    public List<Manufacturer> findAll() {
        List<Manufacturer> manufacturers = manufacturerDAO.findAll();
        logger.info("Returning " + manufacturers.size() + " manufacturers");
        return manufacturers;
    }

    @Override
    public List<Manufacturer> findByName(String name) {
        List<Manufacturer> manufacturers = new LinkedList();
        manufacturers.addAll(manufacturerDAO.findByProperty("name", name));
        logger.info("Returning " + manufacturers.size() + " manufacturers.");
        return manufacturers;
    }
}
