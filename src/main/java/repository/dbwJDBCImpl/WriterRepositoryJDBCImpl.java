package repository.dbwJDBCImpl;

import model.Post;
import model.Region;
import model.Writer;
import repository.WriterRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WriterRepositoryJDBCImpl implements WriterRepository {

    @Override
    public List<Writer> getAll() {
        List<Writer> writers = new ArrayList<>();
        try {
            var statement = DBconnect.getConnectDB().createStatement();
            var resultSet = statement.executeQuery("SELECT users.id_user, users.first_name, users.last_name, regions.name, posts.content, posts.created, posts.updated FROM users\n" +
                    "LEFT JOIN regions ON users.id_user = regions.id_region \n" +
                    "LEFT JOIN posts ON regions.id_region = posts.id_post");
            while (resultSet.next()) {
                Writer writer = new Writer(resultSet.getLong("id_user"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        Collections.singletonList(new Post(resultSet.getLong("id_user"), resultSet.getString("content"), resultSet.getTimestamp("created"), resultSet.getTimestamp("updated"))),
                        new Region(resultSet.getLong("id_user"), resultSet.getString("name")));
                writers.add(writer);
            }
            resultSet.close();
            statement.close();
            DBconnect.getConnectDB().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return writers;
    }

    @Override
    public Writer save(Writer writer) {
        try {
            PreparedStatement prepareStatement = DBconnect.getConnectDB().prepareStatement("INSERT INTO users(id_user,first_name,last_name) VALUES (?, ?, ?)");
            prepareStatement.setLong(1, writer.getId());
            prepareStatement.setString(2, writer.getFirstName());
            prepareStatement.setString(3, writer.getLastName());
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }


    @Override
    public Writer update(Writer writer) {
        try {
            PreparedStatement prepareStatement = DBconnect.getConnectDB().prepareStatement("UPDATE users SET user_id = ?, first_name = ?, last_name = ? WHERE user_id=?");
            prepareStatement.setLong(4, writer.getId());
            prepareStatement.setLong(1, writer.getId());
            prepareStatement.setString(2, writer.getFirstName());
            prepareStatement.setString(3, writer.getLastName());
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }

    @Override
    public boolean deleteById(Long aLong) {
        try {
            PreparedStatement prepareStatement = DBconnect.getConnectDB().prepareStatement("DELETE FROM users WHERE id_user = ?");
            prepareStatement.setLong(1, aLong);
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }

    @Override
    public Writer getById(Long aLong) {
        Writer writer = null;
        try {
            PreparedStatement prepareStatement = DBconnect.getConnectDB().prepareStatement("SELECT users.id_user, users.first_name, users.last_name, regions.name, posts.content, posts.created, posts.updated FROM users LEFT JOIN regions ON users.id_user = regions.id_region LEFT JOIN posts ON regions.id_region = posts.id_post WHERE users.id_user = ?");
            prepareStatement.setLong(1, aLong);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                writer = new Writer(resultSet.getLong("id_user"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        Collections.singletonList(new Post(resultSet.getLong("id_user"), resultSet.getString("content"), resultSet.getTimestamp("created"), resultSet.getTimestamp("updated"))),
                        new Region(resultSet.getLong("id_user"), resultSet.getString("name")));
                resultSet.close();
            }
            prepareStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writer;
    }
}




