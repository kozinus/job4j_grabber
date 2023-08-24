package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HabrCareerParse implements Parse {
    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final int NUMBER_OF_PAGES = 5;

    private final DateTimeParser dateTimeParser;

    public HabrCareerParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    private String retrieveDescription(Document document) {
        StringBuilder out = new StringBuilder();
        document.select(".vacancy-description__text").first().getAllElements().forEach(tag -> {
            switch (tag.tagName()) {
                case "h3":
                case "p":
                    out.append(tag.text()).append("\n");
                    break;
                case "li":
                    out.append("    - ").append(tag.text()).append("\n");
                    break;
                default:
                    break;
            }
        });
        return out.toString();
    }

    private String retrieveTitle(Document document) {
        return document.select(".page-title__title").first().text();
    }

    private LocalDateTime retrieveCreated(Document document) {
        Element row = document.select(".vacancy-header__date").first().child(0).child(0);
        return dateTimeParser.parse(row.attr("datetime"));
    }

    private List<Post> pageParsing(String link) {
        List<Post> posts = new ArrayList<>();
        try {
            Document document = Jsoup.connect(link).get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                try {
                    Document postDoc = Jsoup.connect(String.format("%s%s", SOURCE_LINK,
                            row.select(".vacancy-card__title").first().child(0).attr("href"))).get();
                    posts.add(new Post(1, retrieveTitle(postDoc), retrieveDescription(postDoc), String.format("%s%s", SOURCE_LINK,
                            row.select(".vacancy-card__title").first().child(0).attr("href")),
                            retrieveCreated(postDoc)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return posts;
    }

    @Override
    public List<Post> list(String link) {
        List<Post> posts = new ArrayList<>();
        for (int i = 1; i <= NUMBER_OF_PAGES; i++) {
            posts.addAll(Objects.requireNonNull(pageParsing(String.format("%s%s", link, i))));
        }
        return posts;
    }

    public static void main(String[] args) {
        Parse habr = new HabrCareerParse(new HabrCareerDateTimeParser());
        List<Post> habrJobPosts = habr.list("https://career.habr.com/vacancies/java_developer?page=");
        System.out.println(habrJobPosts.get(0));
    }
}