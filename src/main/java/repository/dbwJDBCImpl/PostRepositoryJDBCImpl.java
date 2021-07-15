package repository.dbwJDBCImpl;

import model.Post;
import repository.PostRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostRepositoryJDBCImpl implements PostRepository {
    @Override
    public Post getById(Long aLong) {
        Post post = null;
        try {
            PreparedStatement prepareStatement = DBconnect.getConnectDB().prepareStatement("SELECT * FROM posts WHERE id_post = ?");
            prepareStatement.setLong(1, aLong);
            ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                post = new Post(resultSet.getLong("id_post"), resultSet.getString("content"), resultSet.getDate("created"), resultSet.getDate("updated"));
                resultSet.close();
            }
            prepareStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;


    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();

        try {
            Statement statement = DBconnect.getConnectDB().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM posts");
            while (resultSet.next()) {
                Post post = new Post(resultSet.getLong("id_post"), resultSet.getString("content"), resultSet.getTimestamp("created"), resultSet.getTimestamp("updated"));
                posts.add(post);
            }
            resultSet.close();
            statement.close();
            DBconnect.getConnectDB().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post update(Post post) {
        try {
            PreparedStatement prepareStatement = DBconnect.getConnectDB().prepareStatement("UPDATE posts SET id_post =?,content = ?, updated=NOW() WHERE id_post=?");
            prepareStatement.setLong(3, post.getId());
            prepareStatement.setLong(1, post.getId());
            prepareStatement.setString(2, post.getContent());
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public boolean deleteById(Long aLong) {
        try {
            PreparedStatement prepareStatement = DBconnect.getConnectDB().prepareStatement("DELETE FROM posts WHERE id_post = ?");
            prepareStatement.setLong(1, aLong);
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Post save(Post post) {
        try {
            PreparedStatement prepareStatement = DBconnect.getConnectDB().prepareStatement("INSERT INTO posts(id_post,content,created) VALUES (?, ?, NOW())");
            prepareStatement.setLong(1, post.getId());
            prepareStatement.setString(2, post.getContent());
            prepareStatement.executeUpdate();
            prepareStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return post;
    }
}

