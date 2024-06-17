package rs.raf.turisticki_vodic_be.repositories.activity;

import rs.raf.turisticki_vodic_be.entities.Activity;

import java.util.List;

public interface ActivityRepository {

    Activity getById(int id);
    List<Activity> getAllActivities();
    Activity createActivity(Activity activity);
    Activity getByName(String name);
}
