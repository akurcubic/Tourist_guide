package rs.raf.turisticki_vodic_be.services.comment;

import rs.raf.turisticki_vodic_be.entities.Comment;
import rs.raf.turisticki_vodic_be.repositories.comment.CommentRepository;

import javax.inject.Inject;
import java.util.List;

public class CommentService {

    @Inject
    CommentRepository commentRepository;

    public List<Comment> getCommentsForArticle(int id){
        return commentRepository.commentsForArticle(id);
    }

    public Comment createComment(Comment comment){
        return commentRepository.createComment(comment);
    }

    public List<Comment> getCommentsForArticleByPage(int id, int page){
        return commentRepository.commentsForArticleByPage(id,page);
    }
 }
