package ru.job4j.grabber;

import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;
import ru.job4j.quartz.AlertRabbit;

import java.io.IOException;
import java.io.InputStream;
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

    public static void main(String[] args) {
        Properties cfg = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("grabber.properties")) {
            cfg.load(in);
            PsqlStore store = new PsqlStore(cfg);
            DateTimeParser formatter = new HabrCareerDateTimeParser();
            Post obj1 = new Post(1, "title", "desc", "link//", formatter.parse("2003-08-24T13:55:16+03:00"));
            System.out.println(obj1);
            Post obj2 = new Post(2, "title1", "desc1", "link//habr", formatter.parse("2023-07-24T13:55:16+03:00"));
            Post obj3 = new Post(3, "title2", "desc2", "link//vacancies", formatter.parse("2023-08-12T13:55:16+02:00"));
            store.save(obj1);
            store.save(obj2);
            store.save(obj3);
            System.out.println(store.getAll());
            System.out.println(store.findById(1));
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

        @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
}