package ru.job4j.ood.lsp.foodstore;

import java.util.ArrayList;

public abstract class AbstractStore implements Store {
    private final AbstractStore store = new Warehouse();

    protected ArrayList<Food> products;

    protected void validate(Food product) {
        if (product.getName() == null || "".equals(product.getName())) {
            throw new IllegalArgumentException("Invalid name.");
        }

        if (product.getCreateDate() == null) {
            throw new IllegalArgumentException("Invalid create date.");
        }

        if (product.getExpiryDate() == null) {
            throw new IllegalArgumentException("Invalid expiry date.");
        }
    }

    public boolean shift(Food product) {

        return true;
    }

    @Override
    public void add(Food product) {
        validate(product);
        if (!shift(product)) {
            products.add(product);
        }
    }
}
