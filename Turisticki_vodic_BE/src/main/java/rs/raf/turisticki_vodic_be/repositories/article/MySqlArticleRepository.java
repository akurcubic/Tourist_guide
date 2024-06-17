package rs.raf.turisticki_vodic_be.repositories.article;

import rs.raf.turisticki_vodic_be.entities.Article;
import rs.raf.turisticki_vodic_be.entities.Destination;
import rs.raf.turisticki_vodic_be.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MySqlArticleRepository extends MySqlAbstractRepository implements ArticleRepository {


    @Override
    public List<Article> getArticlesByDestination(int id) {

        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM articles where destinationId = ? ORDER BY date DESC");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int idArticle = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("date");
                int visits = resultSet.getInt("visits");
                String activities = resultSet.getString("activities");
                int authorId = resultSet.getInt("authorId");
                int destinationId = resultSet.getInt("destinationId");
                articles.add(new Article(idArticle,title,content,date,visits,activities,authorId,destinationId));
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
    public Article createArticle(Article article) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO articles (title,content,date,visits,activities,authorId,destinationId) VALUES(?, ?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setDate(3, article.getDate());
            preparedStatement.setInt(4, article.getVisits());
            preparedStatement.setString(5,article.getActivities());
            preparedStatement.setInt(6, article.getAuthorId());
            preparedStatement.setInt(7, article.getDestinationId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                article.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return article;
    }

    @Override
    public void deleteArticle(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM articles where id = ?");
            preparedStatement.setInt(1, id);
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

    @Override
    public Article changeArticle(Article article) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();


            String sql = "UPDATE articles SET title = ?, content = ?, date = ?, visits = ?,activities = ?, authorId = ?, destinationId = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setDate(3, article.getDate());
            preparedStatement.setInt(4, article.getVisits());
            preparedStatement.setString(5, article.getActivities());
            preparedStatement.setInt(6, article.getAuthorId());
            preparedStatement.setInt(7, article.getDestinationId());
            preparedStatement.setInt(8, article.getId());


            int updated = preparedStatement.executeUpdate();

            if (updated == 1) {
                return article;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeConnection(connection);
            this.closeStatement(preparedStatement);
        }
        return null;
    }

    @Override
    public List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM articles ORDER BY date DESC");
            while (resultSet.next()) {
                articles.add(new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("activities"),
                        resultSet.getInt("authorId"),
                        resultSet.getInt("destinationId")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }
        return articles;
    }


    @Override
    public Article mostVisitedArticleByDestination(int destinationId) {

        Article mostVisitedArticle = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();
            String query = "SELECT * FROM articles WHERE destinationId = ? ORDER BY visits DESC LIMIT 1";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, destinationId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                mostVisitedArticle = new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("activities"),
                        resultSet.getInt("authorId"),
                        resultSet.getInt("destinationId")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return mostVisitedArticle;
    }

    @Override
    public Article getById(int id) {

        Article article = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM articles where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {

                int idArticle = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("date");
                int visits = resultSet.getInt("visits");
                String activities = resultSet.getString("activities");
                int authorId = resultSet.getInt("authorId");
                int destinationId = resultSet.getInt("destinationId");
                article = new Article(idArticle,title,content,date,visits,activities,authorId,destinationId);
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

        return article;
    }

    @Override
    public List<Article> getTop10MostVisitedArticlesLast30Days() {

        List<Article> articles = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            // Calculate the date 30 days ago
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, -30);
            Date date30DaysAgo = new Date(calendar.getTimeInMillis());

            // Query to find the top 10 most visited articles created in the last 30 days
            String query = "SELECT * FROM articles WHERE date >= ? ORDER BY visits DESC LIMIT 10";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, date30DaysAgo);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                articles.add(new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("activities"),
                        resultSet.getInt("authorId"),
                        resultSet.getInt("destinationId")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articles;
    }

    @Override
    public List<Article> get10LatestArticles() {

        List<Article> articles = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = this.newConnection();

            // Query to retrieve the top 10 latest articles sorted by creation date descending
            String query = "SELECT * FROM articles ORDER BY date DESC LIMIT 10";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                articles.add(new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("activities"),
                        resultSet.getInt("authorId"),
                        resultSet.getInt("destinationId")
                ));
            }
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
    public List<Article> getArticlesByDestinationByPage(int page, int id) {
        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            int startIndex = (page - 1) * 10;

            if (startIndex < 0) {
                startIndex = 0;
            }

            String query = "SELECT * FROM articles WHERE destinationId = ? LIMIT ?, 10";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setInt(2, startIndex);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                articles.add(new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("activities"),
                        resultSet.getInt("authorId"),
                        resultSet.getInt("destinationId")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articles;
    }


    @Override
    public List<Article> getAllArticlesByPage(int page) {

        List<Article> articles = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            int startIndex = (page - 1) * 10;

            if (startIndex < 0) {

                startIndex = 0;
            }

            String query = "SELECT * FROM articles ORDER BY date DESC LIMIT ?, 10";
            statement = connection.prepareStatement(query);
            statement.setInt(1, startIndex);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                articles.add(new Article(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("content"),
                        resultSet.getDate("date"),
                        resultSet.getInt("visits"),
                        resultSet.getString("activities"),
                        resultSet.getInt("authorId"),
                        resultSet.getInt("destinationId")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return articles;
    }

}
