package rs.raf.turisticki_vodic_be.repositories.comment;

import rs.raf.turisticki_vodic_be.entities.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> commentsForArticle(int id);
    Comment createComment(Comment comment);
    List<Comment> commentsForArticleByPage(int id,int page);

}
