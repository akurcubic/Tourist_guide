package rs.raf.turisticki_vodic_be.repositories.destination;

import rs.raf.turisticki_vodic_be.entities.Destination;

import rs.raf.turisticki_vodic_be.repositories.MySqlAbstractRepository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlDestinationRepository extends MySqlAbstractRepository implements DestinationRepository {
    @Override
    public List<Destination> getAllDestination() {

        List<Destination> destinations = new ArrayList<>();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from destinations");
            while (resultSet.next()) {
                destinations.add(new Destination(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return destinations;
    }

    @Override
    public Destination createDestination(Destination destination) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            String[] generatedColumns = {"id"};

            preparedStatement = connection.prepareStatement("INSERT INTO destinations (name,description) VALUES(?, ?)", generatedColumns);
            preparedStatement.setString(1, destination.getName());
            preparedStatement.setString(2, destination.getDescription());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                destination.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(preparedStatement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return destination;
    }

    @Override
    public Destination changeDestination(Destination destination) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = this.newConnection();


            String sql = "UPDATE destinations SET name = ?, description = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, destination.getName());
            preparedStatement.setString(2, destination.getDescription());
            preparedStatement.setInt(3, destination.getId());

            int updated = preparedStatement.executeUpdate();

            if (updated == 1) {
                return destination;
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
    public void deleteDestination(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("DELETE FROM destinations where id = ?");
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
    public Destination getByName(String name) {

        Destination destination = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM destinations where name = ?");
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameDestination = resultSet.getString("name");
                String description = resultSet.getString("description");

                destination = new Destination(id, nameDestination, description);
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

        return destination;
    }

    @Override
    public Destination getById(int id) {

        Destination destination = null;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            preparedStatement = connection.prepareStatement("SELECT * FROM destinations where id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int idUser = resultSet.getInt("id");
                String nameDestination = resultSet.getString("name");
                String description = resultSet.getString("description");
                destination = new Destination(idUser, nameDestination, description);
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

        return destination;
    }

    @Override
    public List<Destination> destinationsByPage(int page) {

        List<Destination> destinations = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = this.newConnection();

            int startIndex = (page - 1) * 10;

            if (startIndex < 0) {

                startIndex = 0;
            }

            String query = "SELECT * FROM destinations LIMIT ?, 10";
            statement = connection.prepareStatement(query);
            statement.setInt(1, startIndex);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                destinations.add(new Destination(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("description")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.closeStatement(statement);
            this.closeResultSet(resultSet);
            this.closeConnection(connection);
        }

        return destinations;

    }
}
