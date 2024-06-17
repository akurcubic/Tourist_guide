package rs.raf.turisticki_vodic_be.services.activity;

import rs.raf.turisticki_vodic_be.dto.responses.Response;
import rs.raf.turisticki_vodic_be.entities.Activity;
import rs.raf.turisticki_vodic_be.repositories.activity.ActivityRepository;

import javax.inject.Inject;
import java.util.List;

public class ActivityService {

    @Inject
    ActivityRepository activityRepository;

    public List<Activity> getAllActivities(){
        return activityRepository.getAllActivities();
    }

    public Activity getById(int id){
        return activityRepository.getById(id);
    }

    public Response createActivity(Activity newActivity){

        Activity activity = activityRepository.getByName(newActivity.getName());
        if(activity == null){
            activityRepository.createActivity(newActivity);
            return new Response(200,"Activity created!");
        }
        return new Response(500,"Activity alredy exist!");
    }
}
