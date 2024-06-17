package rs.raf.turisticki_vodic_be.services.article;

import rs.raf.turisticki_vodic_be.dto.responses.Response;
import rs.raf.turisticki_vodic_be.entities.Activity;
import rs.raf.turisticki_vodic_be.entities.Article;
import rs.raf.turisticki_vodic_be.entities.ArticleActivity;
import rs.raf.turisticki_vodic_be.entities.Destination;
import rs.raf.turisticki_vodic_be.repositories.activity.ActivityRepository;
import rs.raf.turisticki_vodic_be.repositories.article.ArticleRepository;
import rs.raf.turisticki_vodic_be.repositories.articleandactivity.ArticleAndActivityRepository;
import rs.raf.turisticki_vodic_be.repositories.destination.DestinationRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ArticleService {


    @Inject
    ArticleRepository articleRepository;

    @Inject
    DestinationRepository destinationRepository;

    @Inject
    ActivityRepository activityRepository;

    @Inject
    ArticleAndActivityRepository articleAndActivityRepository;

    public List<Article> getAllArticlesByPage(int page){
        return articleRepository.getAllArticlesByPage(page);
    }

    public List<Article> getArticlesForDestinationByPage(int page,int id){
        return articleRepository.getArticlesByDestinationByPage(page,id);
    }

    public List<Article> getAllArticles(){
        return articleRepository.getAllArticles();
    }

    public Article getById(int id){
        return articleRepository.getById(id);
    }

    ///odradi za kreiranje i promenu artikla aktivnosti da se menjaju i ubacuju u bazu

    public Article createArticle(Article article){

        String activities = article.getActivities();

        String[] array = activities.split(",");

        for(String activity : array){

            Activity activity1 = activityRepository.getByName(activity);
            if(activity1 == null){

                activity1 = activityRepository.createActivity(new Activity(activity));
            }
            article.getActivityList().add(activity1);
        }

        System.out.println("Id artikla je : " + article.getId());
        for(Activity activity : article.getActivityList()){

            System.out.println("Id aktivitija je " + activity.getId() + " a ime je " + activity.getName());
        }

        // dodavanje u tabelu clanciAktivnosti!

        Article article1 = articleRepository.createArticle(article);

        for(Activity activity : article.getActivityList()){

            ArticleActivity articleActivity = new ArticleActivity(article1.getId(),activity.getId());
            articleAndActivityRepository.create(articleActivity);
        }
        return article1;
    }

    public Response changeArticle(Article changedArticle){

        Article article = articleRepository.getById(changedArticle.getId());
        if(article == null){
            return new Response(500,"Article not found");
        }
        article.setTitle(changedArticle.getTitle());
        article.setContent(changedArticle.getContent());
        //article.setDate(changedArticle.getDate());
        //article.setVisits(article.getVisits());
        article.setActivities(changedArticle.getActivities());
        article.setDestinationId(changedArticle.getDestinationId());

        String activities = article.getActivities();

        String[] array = activities.split(",");

        article.getActivityList().clear();

        for(String activity : array){

            Activity activity1 = activityRepository.getByName(activity);
            if(activity1 == null){

                activity1 = activityRepository.createActivity(new Activity(activity));
            }
            article.getActivityList().add(activity1);
        }

        // azuriranje tabele clanciAktivnosti!

        articleAndActivityRepository.deleteByArticleId(article.getId());

        for(Activity activity : article.getActivityList()){

            ArticleActivity articleActivity = new ArticleActivity(article.getId(),activity.getId());
            articleAndActivityRepository.create(articleActivity);
        }

        Article a = articleRepository.changeArticle(article);
        if(a != null)
            return new Response(200,"Article changed!");
        else
            return  new Response(500,"Error while changing article");
    }

    public void deleteArticle(int id){

        articleAndActivityRepository.deleteByArticleId(id);
        articleRepository.deleteArticle(id);
    }

    public List<Article> getArticlesByDestination(int id){
        return articleRepository.getArticlesByDestination(id);
    }

    public List<Article> mostVisitedArticlesByDestinations(){

        List<Article> filteredArticles = new ArrayList<>();
        List<Destination> destinations = destinationRepository.getAllDestination();

        for(Destination destination : destinations){
            Article mostVisited = articleRepository.mostVisitedArticleByDestination(destination.getId());
            filteredArticles.add(mostVisited);
        }
        return filteredArticles;
    }

    public List<Article> getTop10MostVisitedArticlesLast30Days(){
        return articleRepository.getTop10MostVisitedArticlesLast30Days();
    }

    public Article incrementArticleVisits(int articleId){

        Article article = articleRepository.getById(articleId);
        article.setVisits(article.getVisits() + 1);
        article = articleRepository.changeArticle(article);
        return article;
    }

    public List<Article> get10LatestArticles(){
        return articleRepository.get10LatestArticles();
    }
}
