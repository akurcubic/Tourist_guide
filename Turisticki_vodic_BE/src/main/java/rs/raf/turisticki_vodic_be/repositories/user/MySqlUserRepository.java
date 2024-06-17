package rs.raf.turisticki_vodic_be.repositories.user;

import rs.raf.turisticki_vodic_be.dto.requests.ChangeUserRequest;
import rs.raf.turisticki_vodic_be.entities.Destination;
import rs.raf.turisticki_vodic_be.entities.User;
import rs.raf.turisticki_vodic_be.repositories.MySqlAbstractRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserRepository extends MySqlAbstractRepository implements UserRepository {

    @Override
    public User getByEmail(String email) {

        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM users where email = ?");
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String userEmail = resultSet.getString("email");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String type = resultSet.getString("type");
                String status = resultSet.getString("status");
                String password = resultSet.getString("password");
                System.out.println("GetByEmail vraca usera sa sifrom " + password);
                user = new User(id,userEmail,name,surname,type,status,password);
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

        return user;


    }

    @Override
    public User create(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO users (email,name,surname,type,status,password) VALUES(?, ?, ?, ?, ?, ?)", generatedColumns);
            preparedStatement.setString(1,user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getType());
            preparedStatement.setString(5,user.getStatus());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return user;
    }

    @Override
    public User changeUser(User user) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();


            String sql = "UPDATE users SET email = ?, name = ?, surname = ?, type = ?, status = ?, password = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getType());
            preparedStatement.setString(5, user.getStatus());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getId());

            int updated = preparedStatement.executeUpdate();

            if (updated == 1) {
                return user;
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
    public User getById(int id) {

        User user = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM users where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int idUser = resultSet.getInt("id");
                String userEmail = resultSet.getString("email");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String type = resultSet.getString("type");
                String status = resultSet.getString("status");
                String password = resultSet.getString("password");
                user = new User(idUser,userEmail,name,surname,type,status,password);
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

        return user;
    }

    @Override
    public User getByName(String name) {

        User user = null;
        System.out.println("pozvao sam se " + name);

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM users where name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int id = resultSet.getInt("id");
                String userEmail = resultSet.getString("email");
                String nameUser = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String type = resultSet.getString("type");
                String status = resultSet.getString("status");
                String password = resultSet.getString("password");
                System.out.println("GetByEmail vraca usera sa sifrom " + password);
                user = new User(id,userEmail,nameUser,surname,type,status,password);
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

        return user;
    }

    @Override
    public List<User> getAllUserByPage(int page) {

        List<User> users = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            int startIndex = (page - 1) * 10;

            if (startIndex < 0) {

                startIndex = 0;
            }

            String query = "SELECT * FROM users LIMIT ?, 10";
            statement = connection.prepareStatement(query);
            statement.setInt(1, startIndex);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),resultSet.getString("email"), resultSet.getString("name"), resultSet.getString("surname"),resultSet.getString("type"),resultSet.getString("status"),resultSet.getString("password")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"),resultSet.getString("email"), resultSet.getString("name"), resultSet.getString("surname"),resultSet.getString("type"),resultSet.getString("status"),resultSet.getString("password")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return users;
    }
}
