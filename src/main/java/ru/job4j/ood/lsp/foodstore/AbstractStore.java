package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;

public abstract class AbstractStore implements Store {

    protected Store store;

    protected ArrayList<Food> products;

    public AbstractStore(Store store) {
        this.store = store;
        this.products = new ArrayList<>();
    }

    @Override
    public boolean check(Food product) {
        return false;
    }

    @Override
    public ArrayList<Food> getProducts() {
        return products;
    }

    @Override
    public void add(Food product) {
        products.add(product);
    }

    @Override
    public Store shift() {
        return store;
    }
}
