package rs.raf.turisticki_vodic_be.repositories.articleandactivity;


import rs.raf.turisticki_vodic_be.entities.ArticleActivity;

import java.util.List;

public interface ArticleAndActivityRepository {

    ArticleActivity create(ArticleActivity articleActivity);
    List<ArticleActivity> getActivitiesForArticle(int articleId);
    List<ArticleActivity> getArticlesByActivity(int activityId,int page);
    ArticleActivity getById(int id);
    void deleteByArticleId(int id);

}
