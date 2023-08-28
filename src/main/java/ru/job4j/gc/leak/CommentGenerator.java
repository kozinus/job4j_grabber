package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    public final String path = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";

    private final List<Comment> comments = new ArrayList<>();
    private List<String> phrases;
    private final UserGenerator userGenerator;
    private final Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        try {
            phrases = read(path);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        for (int i = 0; i < 50; i++) {
            String comment = new StringBuilder().append(phrases.get(random.nextInt(phrases.size()))).append(" ")
                    .append(phrases.get(random.nextInt(phrases.size()))).append(" ")
                    .append(phrases.get(random.nextInt(phrases.size()))).toString();
            comments.add(new Comment(comment,
                    userGenerator.randomUser()));
        }
    }
}