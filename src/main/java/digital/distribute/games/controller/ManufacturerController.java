package digital.distribute.games.controller;

import digital.distribute.games.entity.Manufacturer;
import digital.distribute.games.service.ManufacturerService;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * @author Pavel Stibal
 */
@Model
public class ManufacturerController {
    @Inject
    private Logger logger;

    @Inject
    private ManufacturerService manufacturerService;

    @Produces
    @Named(value = "newManufacturer")
    private Manufacturer newManufacturer;

    @PostConstruct
    public void initNewGame() {
        newManufacturer = new Manufacturer();
    }
    public Manufacturer getManufacturer(final long id) throws IOException {
        try {
            Manufacturer manufacturer = manufacturerService.get(id);
            logger.fine("Manufacturer with id " + id + " found.");
            return manufacturer;
        } catch (Exception e) {
            logger.severe("Error during getManufacturer() " + id + ": " + e.getMessage());
            throw new WebApplicationException();
        }
    }
}
