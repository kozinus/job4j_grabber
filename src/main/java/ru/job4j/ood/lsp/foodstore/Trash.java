package ru.job4j.ood.lsp.foodstore;

public class Trash extends AbstractStore {
    public Trash(Store store) {
        super(store);
    }

    @Override
    public boolean check(Food product) {
        return true;
    }
}
