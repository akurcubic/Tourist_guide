package rs.raf.turisticki_vodic_be.resources.comment;

import rs.raf.turisticki_vodic_be.entities.Comment;
import rs.raf.turisticki_vodic_be.services.comment.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("comments")
public class CommentResources {

    @Inject
    CommentService commentService;

    @GET
    @Path("/article/{articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getCommentsForArticle(@PathParam("articleId") int articleId){

        return commentService.getCommentsForArticle(articleId);
    }

    @GET
    @Path("/article/{articleId}/{page}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getCommentsForArticleByPage(@PathParam("articleId") int articleId,@PathParam("page") int page){

        return commentService.getCommentsForArticleByPage(articleId,page);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Comment createComment(Comment comment) {
        return commentService.createComment(comment);
    }
}
