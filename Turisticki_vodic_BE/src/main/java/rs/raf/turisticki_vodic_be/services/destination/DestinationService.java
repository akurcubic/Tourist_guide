package rs.raf.turisticki_vodic_be.services.destination;

import rs.raf.turisticki_vodic_be.dto.responses.Response;
import rs.raf.turisticki_vodic_be.entities.Article;
import rs.raf.turisticki_vodic_be.entities.Destination;
import rs.raf.turisticki_vodic_be.repositories.article.ArticleRepository;
import rs.raf.turisticki_vodic_be.repositories.destination.DestinationRepository;

import javax.inject.Inject;
import java.util.List;

public class DestinationService {

    @Inject
    DestinationRepository destinationRepository;

    @Inject
    ArticleRepository articleRepository;

    public List<Destination> getDestinationByPage(int page){
        return destinationRepository.destinationsByPage(page);
    }

    public Destination getByName(String name){
        return destinationRepository.getByName(name);
    }

    public List<Destination> getAllDestinations(){
        return destinationRepository.getAllDestination();
    }

    public Destination getById(int id){
        return destinationRepository.getById(id);
    }

    public Response creteDestination(Destination newDestination){

        Destination destination = destinationRepository.getByName(newDestination.getName());
        if(destination == null){

            destinationRepository.createDestination(newDestination);
            return new Response(200,"Desstination created!");
        }
        return new Response(500,"Desstination alredy exists!");
    }

    public Response changeDestination(Destination changedDestination){

        Destination destination = destinationRepository.getById(changedDestination.getId());
        if(destination == null)
            return new Response(500,"Destination not found!");
        destination.setName(changedDestination.getName());
        destination.setDescription(changedDestination.getDescription());

        Destination dest = destinationRepository.changeDestination(destination);
        if(dest != null)
            return new Response(200,"Destination changed!");
        else
            return new Response(500,"Error while changing destination!");

    }

    public Response deleteDestination(int id){

        List<Article> articles = articleRepository.getAllArticles();

        for(Article article : articles){

            if(article.getDestinationId() == id){

                return new Response(500, "Deleting the destination is not possible because there are articles related to it");
            }
        }
        destinationRepository.deleteDestination(id);
        return new Response(200,"Destination deleted!");
    }
}
