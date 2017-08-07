package digital.distribute.games.rest;


import digital.distribute.games.entity.Revue;
import digital.distribute.games.service.RevueService;

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
@Path("/revue")
public class RevueResourceRESTService {
    @Inject
    private Logger logger;

    @Inject
    private Validator validator;

    @Inject
    private RevueService revueService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Revue> findAll() {
        return revueService.findAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Revue get(@PathParam("id") long id) {
        final Revue revue = revueService.get(id);
        if (revue == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return revue;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Revue revue) {
        Response.ResponseBuilder builder;

        try {
            validate(revue);
            revueService.create(revue);
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

    private void validate(Revue revue) throws ConstraintViolationException {
        Set<ConstraintViolation<Revue>> violations = validator.validate(revue);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
    }
}
