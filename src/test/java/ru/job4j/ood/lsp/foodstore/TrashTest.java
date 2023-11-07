package ru.job4j.ood.lsp.foodstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TrashTest {
    private Store store;
    private ControlQuality control;

    @BeforeEach
    void init() {
        store = new Warehouse(new Shop(new Trash(null)));
        control = new ControlQuality();
    }

    @Disabled
    @Test
    public void pushApplesInTrash() {
        Food product = new Food("Apples", LocalDate.of(2023, 10, 10), LocalDate.of(2023, 9, 5), 120, 0);
        control.pushInStore(product, store);
        assertThat(store.shift().shift().getProducts().contains(product)).isTrue();
    }

}