package ru.job4j.ood.lsp.foodstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Shop extends AbstractStore {
    public Shop(Store store) {
        super(store);
    }

    @Override
    public boolean check(Food product) {
        return ChronoUnit.DAYS.between(product.getCreateDate(), LocalDate.now()) < ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate());
    }

    @Override
    public void add(Food product) {
        if (ChronoUnit.DAYS.between(product.getCreateDate(), LocalDate.now())
                > ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate()) * 0.75) {
            product.setPrice(product.getPrice() * 0.8);
            product.setDiscount(100 - ((100 - product.getDiscount()) * 0.8));
        }
        products.add(product);
    }
}
