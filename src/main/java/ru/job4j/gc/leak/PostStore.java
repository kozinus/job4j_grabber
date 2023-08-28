package ru.job4j.gc.leak;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PostStore {

    private final Map<Integer, Post> posts = new HashMap<>();

    private int atomicInteger = 1;

    public void add(Post post) {
        int id = atomicInteger++;
        post.setId(id);
        posts.put(id, post);
    }

    public void removeAll() {
        posts.clear();
    }

    public Collection<Post> getPosts() {
        return posts.values();
    }
}