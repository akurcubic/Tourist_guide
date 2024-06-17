package rs.raf.turisticki_vodic_be.resources.destination;

import rs.raf.turisticki_vodic_be.annotations.Authorize;
import rs.raf.turisticki_vodic_be.dto.responses.Response;
import rs.raf.turisticki_vodic_be.entities.Destination;
import rs.raf.turisticki_vodic_be.services.destination.DestinationService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/destinations")
public class DestinationResources {

    @Inject
    private DestinationService destinationService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Destination getById(@PathParam("id") Integer id) {
        return destinationService.getById(id);
    }

    @GET
    @Path("/destination_name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Destination getByName(@PathParam("name") String name) {
        return destinationService.getByName(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Destination> getAllDestinations() {
        return destinationService.getAllDestinations();
    }


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("CONTENT_CREATOR")
    public Response createDestination(Destination destination) {
        return destinationService.creteDestination(destination);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("CONTENT_CREATOR")
    public Response changeDestination(Destination destination) {
        return destinationService.changeDestination(destination);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Authorize("CONTENT_CREATOR")
    public Response delete(@PathParam("id") Integer id) {
        return destinationService.deleteDestination(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/page/{id}")
    public List<Destination> getDestinationsByPage(@PathParam("id") Integer id) {
        return destinationService.getDestinationByPage(id);
    }
}
