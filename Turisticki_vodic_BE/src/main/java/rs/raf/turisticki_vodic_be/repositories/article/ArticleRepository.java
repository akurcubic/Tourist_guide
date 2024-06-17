package rs.raf.turisticki_vodic_be.repositories.article;

import rs.raf.turisticki_vodic_be.entities.Article;

import java.util.List;

public interface ArticleRepository {

    List<Article> getArticlesByDestination(int id);
    Article createArticle(Article article);
    void deleteArticle(int id);
    Article changeArticle(Article article);
    List<Article> getAllArticles();
    Article mostVisitedArticleByDestination(int destinationId);
    Article getById(int id);
    List<Article> getTop10MostVisitedArticlesLast30Days();
    List<Article> get10LatestArticles();
    List<Article> getArticlesByDestinationByPage(int page, int id);
    List<Article> getAllArticlesByPage(int page);


}
