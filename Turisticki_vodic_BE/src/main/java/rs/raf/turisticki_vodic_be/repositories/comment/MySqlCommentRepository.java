package rs.raf.turisticki_vodic_be.repositories.comment;

import rs.raf.turisticki_vodic_be.entities.Article;
import rs.raf.turisticki_vodic_be.entities.Comment;
import rs.raf.turisticki_vodic_be.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlCommentRepository extends MySqlAbstractRepository implements CommentRepository {


    @Override
    public List<Comment> commentsForArticle(int article) {

        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM comments WHERE article = ? ORDER BY date DESC");
            preparedStatement.setInt(1, article);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int idComment = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("date");
                int idArticle = resultSet.getInt("article");

                comments.add(new Comment(idComment,author,content,date,idArticle));
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

        return comments;
    }

    @Override
    public Comment createComment(Comment comment) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO comments (author,content,date,article) VALUES(?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1,comment.getAuthor());
            preparedStatement.setString(2, comment.getContent());
            preparedStatement.setDate(3,comment.getDate());
            preparedStatement.setInt(4,comment.getArticleId());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                comment.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comment;
    }

    @Override
    public List<Comment> commentsForArticleByPage(int id, int page) {

        List<Comment> comments = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            int startIndex = (page - 1) * 10;

            if (startIndex < 0) {
                startIndex = 0;
            }

            String query = "SELECT * FROM comments WHERE article = ? ORDER BY date DESC LIMIT ?, 10";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.setInt(2, startIndex);

            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                int idComment = resultSet.getInt("id");
                String author = resultSet.getString("author");
                String content = resultSet.getString("content");
                Date date = resultSet.getDate("date");
                int idArticle = resultSet.getInt("article");

                comments.add(new Comment(idComment,author,content,date,idArticle));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return comments;
    }
}
