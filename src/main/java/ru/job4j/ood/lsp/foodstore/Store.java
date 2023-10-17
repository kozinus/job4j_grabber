package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;

public interface Store {
    void add(Food product);

    Store shift();

    boolean check(Food product);

    ArrayList<Food> getProducts();
}
