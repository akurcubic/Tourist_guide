package rs.raf.turisticki_vodic_be.entities;

public class ArticleActivity {

    private int id;
    private int articleId;
    private int activityId;

    public ArticleActivity(int id, int articleId, int activityId) {
        this.id = id;
        this.articleId = articleId;
        this.activityId = activityId;
    }

    public ArticleActivity(){


    }

    public int getId() {
        return id;
    }

    public ArticleActivity(int articleId, int activityId) {
        this.articleId = articleId;
        this.activityId = activityId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }
}
