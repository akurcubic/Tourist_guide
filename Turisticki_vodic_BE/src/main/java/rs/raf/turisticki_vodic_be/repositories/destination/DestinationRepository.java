package rs.raf.turisticki_vodic_be.repositories.destination;

import rs.raf.turisticki_vodic_be.entities.Destination;


import java.util.List;

public interface DestinationRepository {

    List<Destination> getAllDestination();
    Destination createDestination(Destination destination);
    Destination changeDestination (Destination destination);
    void deleteDestination(int id);
    Destination getByName(String name);
    Destination getById(int id);
    List<Destination> destinationsByPage(int page);

}
