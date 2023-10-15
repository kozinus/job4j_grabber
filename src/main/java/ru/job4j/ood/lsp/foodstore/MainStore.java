package ru.job4j.ood.lsp.foodstore;

import java.time.LocalDate;

public class MainStore {
    public static void main(String[] args) {
        Store store = new Warehouse(new Shop(new Trash(null)));
        Food product = new Food("Cola", LocalDate.of(2024, 5, 21), LocalDate.of(2023, 4, 22), 55, 0);
        while (!store.check(product)) {
            store = store.shift();
        }
        store.add(product);
    }
}