package digital.distribute.games.rest;

import digital.distribute.games.entity.Game;
import digital.distribute.games.service.GameService;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;
import java.util.logging.Logger;

/**
 * @author Pavel Stibal
 */
@Path("/game")
public class GameResourceRESTService {
    @Inject
    private Logger logger;

    @Inject
    private Validator validator;

    @Inject
    private GameService gameService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> findAll() {
        return gameService.findAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game get(@PathParam("id") long id) {
        final Game game = gameService.get(id);
        if (game == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return game;
    }

    @GET
    @Path("/category/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getGamesForCategory(@PathParam("id") long id) {
        return gameService.findByCategoryID(id);
    }

    @GET
    @Path("/manufacturer/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getGamesForManufacturer(@PathParam("id") long id) {
        return gameService.findByManufacturerID(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Game game) {
        Response.ResponseBuilder builder;

        try {
            validate(game);
            gameService.create(game);
            logger.info("Created a new entity.");
            builder = Response.ok();
        } catch (ConstraintViolationException ce) {
            builder = createViolationResponse(ce.getConstraintViolations());
        } catch (Exception e) {
            Map<String, String> responseError = new HashMap();
            responseError.put("error", e.getMessage());
            builder = Response.status(Response.Status.BAD_REQUEST).entity(responseError);
        }

        return builder.build();
    }

    private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
        logger.info("Validation was completed, but violations was found: " + violations.size());
        Map<String, String> responseObj = new HashMap();

        for (ConstraintViolation<?> violation : violations) {
            responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
    }

    private void validate(Game game) throws ConstraintViolationException {
        Set<ConstraintViolation<Game>> violations = validator.validate(game);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
    }
}
