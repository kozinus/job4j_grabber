package ru.job4j.grabber;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
    private int id;
    private String title;
    private String link;
    private String description;
    private LocalDateTime created;

    public Post(int id, String link, String description) throws IOException {
        this.id = id;
        this.link = link;
        this.description = description;
        this.created = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post post)) {
            return false;
        }
        return id == post.id && link.equals(post.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", link='" + link + '\''
                + ", description='" + description + '\''
                + ", created=" + created
                + '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void main(String[] args) throws IOException {
        String link = "https://career.habr.com/vacancies/1000126258";
        Post post = new Post(1, link, HabrCareerParse.retrieveDescription(link));
        System.out.println(post.getDescription());
    }
}
