package ru.job4j.ood.lsp.foodstore;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Warehouse extends AbstractStore {
    public Warehouse(Store store) {
        super(store);
    }

    @Override
    public boolean check(Food product) {
        return ChronoUnit.DAYS.between(product.getCreateDate(), LocalDate.now()) < ChronoUnit.DAYS.between(product.getCreateDate(), product.getExpiryDate()) * 0.25;
    }

    @Override
    public ArrayList<Food> getProducts() {
        return super.getProducts();
    }
}
