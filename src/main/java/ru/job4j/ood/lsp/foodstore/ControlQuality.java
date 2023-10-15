package ru.job4j.ood.lsp.foodstore;

public class ControlQuality {
    private void validate(Food product) {
        if (product.getName() == null || "".equals(product.getName())) {
            throw new IllegalArgumentException("Invalid name.");
        }

        if (product.getCreateDate() == null) {
            throw new IllegalArgumentException("Invalid create date.");
        }

        if (product.getExpiryDate() == null) {
            throw new IllegalArgumentException("Invalid expiry date.");
        }

        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Invaild price.");
        }

        if (product.getDiscount() < 0 || product.getDiscount() > 100) {
            throw new IllegalArgumentException("Invaild discount.");
        }
    }

    public void pushInStore(Food product, Store store) {
        validate(product);
        while (!store.check(product)) {
            store = store.shift();
        }
        store.add(product);
    }
}
