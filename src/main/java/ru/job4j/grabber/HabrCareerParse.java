package ru.job4j.grabber;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.IOException;

public class HabrCareerParse {

    private static final String SOURCE_LINK = "https://career.habr.com";

    private static final String PAGE_LINK = String.format("%s/vacancies/java_developer?page=", SOURCE_LINK);

    public static String retrieveDescription(String link) throws IOException {
        StringBuilder out = new StringBuilder();
        Connection connection = Jsoup.connect(link);
        Document document = connection.get();
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

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 5; i++) {
            Connection connection = Jsoup.connect(String.format("%s%s", PAGE_LINK, i));
            Document document = connection.get();
            Elements rows = document.select(".vacancy-card__inner");
            rows.forEach(row -> {
                Element titleElement = row.select(".vacancy-card__title").first();
                Element dateElement = row.select(".vacancy-card__date").first().child(0);
                Element linkElement = titleElement.child(0);
                String vacancyName = titleElement.text();
                DateTimeParser parser = new HabrCareerDateTimeParser();
                String link = String.format("%s%s", SOURCE_LINK, linkElement.attr("href"));
                System.out.printf("%s %s %s%n", parser.parse(dateElement.attr("datetime")), vacancyName, link);
            });
        }
    }
}