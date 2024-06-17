package rs.raf.turisticki_vodic_be.repositories.articleandactivity;

import rs.raf.turisticki_vodic_be.entities.ArticleActivity;
import rs.raf.turisticki_vodic_be.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlArticleAndActivityRepository extends MySqlAbstractRepository implements ArticleAndActivityRepository {

    @Override
    public ArticleActivity create(ArticleActivity articleActivity) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO articlesactivities (articleId,activityId) VALUES(?, ?)", generatedColumns);
            preparedStatement.setInt(1, articleActivity.getArticleId());
            preparedStatement.setInt(2, articleActivity.getActivityId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                articleActivity.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articleActivity;
    }

    @Override
    public List<ArticleActivity> getActivitiesForArticle(int articleId) {

        List<ArticleActivity> activities = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM articlesactivities where articleId = ?");
            preparedStatement.setInt(1, articleId);

            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                int idArticle = resultSet.getInt("articleId");
                int activityId = resultSet.getInt("activityId");

                activities.add(new ArticleActivity(id,idArticle,activityId));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return activities;
    }

    @Override
    public List<ArticleActivity> getArticlesByActivity(int activityId,int page) {

        List<ArticleActivity> articles = new ArrayList<>();

        Connection connection = null;

        int startIndex = (page - 1) * 10;

        if (startIndex < 0) {
            startIndex = 0;
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM articlesactivities where activityId = ? LIMIT ?, 10");
            preparedStatement.setInt(1, activityId);
            preparedStatement.setInt(2,startIndex);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                int idArticle = resultSet.getInt("articleId");
                int idActivity = resultSet.getInt("activityId");

                articles.add(new ArticleActivity(id,idArticle,idActivity));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articles;
    }

    @Override
    public ArticleActivity getById(int id) {

        ArticleActivity articleActivity = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM articlesactivities where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {

                int idArticleActivity = resultSet.getInt("id");
                int articleId = resultSet.getInt("articleId");
                int activityId = resultSet.getInt("activityId");

                articleActivity = new ArticleActivity(idArticleActivity,articleId,activityId);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articleActivity;
    }

    @Override
    public void deleteByArticleId(int articleId) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM articlesactivities where articleId = ?");
            preparedStatement.setInt(1, articleId);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeConnection(connection);
        }
    }
}
