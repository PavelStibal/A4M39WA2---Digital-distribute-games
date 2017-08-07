package digital.distribute.games.rest;

import digital.distribute.games.entity.Oorder;
import digital.distribute.games.service.OrderService;

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
@Path("/order")
public class OrderResourceRESTService {
    @Inject
    private Logger logger;

    @Inject
    private Validator validator;

    @Inject
    private OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Oorder> findAll() {
        return orderService.findAll();
    }

    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Oorder get(@PathParam("id") long id) {
        final Oorder oorder = orderService.get(id);
        if (oorder == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return oorder;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Oorder oorder) {
        Response.ResponseBuilder builder;

        try {
            validate(oorder);
            orderService.create(oorder);
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

    private void validate(Oorder oorder) throws ConstraintViolationException {
        Set<ConstraintViolation<Oorder>> violations = validator.validate(oorder);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
    }
}
