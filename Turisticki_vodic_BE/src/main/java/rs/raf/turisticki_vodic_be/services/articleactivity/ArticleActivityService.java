package rs.raf.turisticki_vodic_be.services.articleactivity;

import rs.raf.turisticki_vodic_be.dto.responses.Response;
import rs.raf.turisticki_vodic_be.entities.Activity;
import rs.raf.turisticki_vodic_be.entities.Article;
import rs.raf.turisticki_vodic_be.entities.ArticleActivity;
import rs.raf.turisticki_vodic_be.repositories.activity.ActivityRepository;
import rs.raf.turisticki_vodic_be.repositories.article.ArticleRepository;
import rs.raf.turisticki_vodic_be.repositories.articleandactivity.ArticleAndActivityRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ArticleActivityService {

    @Inject
    ArticleAndActivityRepository activityAndActivityRepository;

    @Inject
    ActivityRepository activityRepository;

    @Inject
    ArticleRepository articleRepository;

    public ArticleActivity create(ArticleActivity articleActivity){

        return activityAndActivityRepository.create(articleActivity);
    }

    public List<Activity> getActivitiesForArticle(int articleId){

        List<ArticleActivity> activitiesForArticle = activityAndActivityRepository.getActivitiesForArticle(articleId);

        List<Activity> activities = new ArrayList<>();

        for(ArticleActivity articleActivity : activitiesForArticle){

            Activity activity = activityRepository.getById(articleActivity.getActivityId());
            activities.add(activity);
        }

        return activities;
    }

    public List<Article> getArticlesByActivity(int activityId,int page){

        List<ArticleActivity> articlesForActivity = activityAndActivityRepository.getArticlesByActivity(activityId,page);

        List<Article> articles = new ArrayList<>();

        for(ArticleActivity articleActivity : articlesForActivity){

            Article article = articleRepository.getById(articleActivity.getArticleId());
            articles.add(article);
        }

        return articles;
    }

    public Response deleteByArticleId(int articleId){

        activityAndActivityRepository.deleteByArticleId(articleId);
        return new Response(200,"Deleted by article ID in table articlesactivities");
    }
}
