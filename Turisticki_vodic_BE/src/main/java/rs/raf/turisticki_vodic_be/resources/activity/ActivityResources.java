package rs.raf.turisticki_vodic_be.resources.activity;

import rs.raf.turisticki_vodic_be.annotations.Authorize;
import rs.raf.turisticki_vodic_be.dto.responses.Response;
import rs.raf.turisticki_vodic_be.entities.Activity;

import rs.raf.turisticki_vodic_be.services.activity.ActivityService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/activities")
public class ActivityResources {

    @Inject
    ActivityService activityService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("CONTENT_CREATOR")
    public Activity getById(@PathParam("id") Integer id) {
        return activityService.getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("CONTENT_CREATOR")
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("CONTENT_CREATOR")
    public Response createActivity(Activity activity) {
        return activityService.createActivity(activity);
    }
}
