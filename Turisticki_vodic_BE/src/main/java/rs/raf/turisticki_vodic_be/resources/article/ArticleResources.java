package rs.raf.turisticki_vodic_be.resources.article;


import rs.raf.turisticki_vodic_be.annotations.Authorize;
import rs.raf.turisticki_vodic_be.dto.responses.Response;
import rs.raf.turisticki_vodic_be.entities.Article;
import rs.raf.turisticki_vodic_be.services.article.ArticleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/articles")
public class ArticleResources {

    @Inject
    ArticleService articleService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Article getById(@PathParam("id") Integer id) {
        return articleService.getById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("CONTENT_CREATOR")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/page/{id}")
    @Authorize("CONTENT_CREATOR")
    public List<Article> getAllArticlesByPage(@PathParam("id") Integer id) {
        return articleService.getAllArticlesByPage(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/page/{id}/{page}")
    public List<Article> getArticlesByDestinationByPage(@PathParam("id") Integer id, @PathParam("page") Integer page) {
        return articleService.getArticlesForDestinationByPage(page,id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("CONTENT_CREATOR")
    public Article createArticle(Article article) {
        return articleService.createArticle(article);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Authorize("CONTENT_CREATOR")
    public Response changeArticle(Article article) {
        return articleService.changeArticle(article);
    }

    @DELETE
    @Path("/{id}")
    @Authorize("CONTENT_CREATOR")
    public void delete(@PathParam("id") Integer id) {
        articleService.deleteArticle(id);
    }

    @GET
    @Path("/destination/{destinationId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getArticlesByDestination(@PathParam("destinationId") int destinationId) {
        return articleService.getArticlesByDestination(destinationId);
    }

    @GET
    @Path("most-visited")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> getTop10MostVisitedArticlesLast30Days(){
        return articleService.getTop10MostVisitedArticlesLast30Days();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/increment_visits/{articleId}")
    public Article incrementArticleVisits(@PathParam("articleId") int articleId) {
        return articleService.incrementArticleVisits(articleId);
    }

    @GET
    @Path("latest-articles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Article> get10LatestArticles(){
        return articleService.get10LatestArticles();
    }
}
