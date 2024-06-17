package rs.raf.turisticki_vodic_be.resources.articleactivity;

import rs.raf.turisticki_vodic_be.annotations.Authorize;
import rs.raf.turisticki_vodic_be.dto.responses.Response;
import rs.raf.turisticki_vodic_be.entities.Activity;
import rs.raf.turisticki_vodic_be.entities.Article;
import rs.raf.turisticki_vodic_be.entities.ArticleActivity;
import rs.raf.turisticki_vodic_be.services.articleactivity.ArticleActivityService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/articlesactivities")
public class ArticleActivityResources {

    @Inject
    ArticleActivityService articleActivityService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("CONTENT_CREATOR")
    public ArticleActivity createArticleActivity(ArticleActivity articleActivity) {
        return articleActivityService.create(articleActivity);
    }

    @GET
    @Path("/article/{articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Activity> getActivitiesForArticle(@PathParam("articleId") int articleId) {
        return articleActivityService.getActivitiesForArticle(articleId);
    }

    @GET
    @Path("/activity/{activityId}/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getArticlesByActivity(@PathParam("activityId") int activityId, @PathParam("page") int page) {
        return articleActivityService.getArticlesByActivity(activityId,page);
    }

    @DELETE
    @Path("/article/{articleId}")
    @Authorize("CONTENT_CREATOR")
    public Response delete(@PathParam("articleId") Integer articleId) {
        return articleActivityService.deleteByArticleId(articleId);
    }

}
