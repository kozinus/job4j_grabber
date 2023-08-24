package ru.job4j.grabber;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store {

    private final Connection cnn;

    public PsqlStore(Properties cfg) throws SQLException {
        String url;
        String login;
        String password;
        try {
            Class.forName(cfg.getProperty("driver_class"));
            url = cfg.getProperty("url");
            login = cfg.getProperty("username");
            password = cfg.getProperty("password");
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        cnn = DriverManager.getConnection(url, login, password);
    }

    @Override
    public void save(Post post) {
        try (Statement statement = cnn.createStatement()) {
            var resSet = statement.executeQuery(String.format(
                    "SELECT * FROM %s ORDER BY ID DESC LIMIT 1", "post"
            ));
            if (resSet.next()) {
                post.setId(resSet.getInt("id") + 1);
            }
            String title = post.getTitle();
            String desc = post.getDescription();
            String link = post.getLink();
            String created = post.getCreated().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            String sql = String.format(
                    "INSERT INTO %s(id, name, text, link, created)"
                            + "VALUES(%d, '%s', '%s', '%s', '%s')"
                            + "ON CONFLICT (link)"
                            + "DO UPDATE SET name = '%s', text = '%s', created = '%s'",
                    "post", post.getId(), title, desc, link, created,
                    title, desc, created);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Post> getAll() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement statement = cnn.prepareStatement("select * from post;");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                posts.add(new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("text"),
                        resultSet.getString("link"),
                        LocalDateTime.parse(resultSet.getString("created").replaceAll(" ", "T"), formatter)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public Post findById(int id) {
        Post out = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        try (PreparedStatement statement = cnn.prepareStatement(String.format("select * from post where id = %d;", id));
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                out = new Post(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("text"),
                        resultSet.getString("link"),
                        LocalDateTime.parse(resultSet.getString("created").replaceAll(" ", "T"), formatter)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
}