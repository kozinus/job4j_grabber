package ru.job4j.ood.lsp.foodstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WarehouseTest {
    private Store store;
    private ControlQuality control;

    @BeforeEach
    void init() {
        store = new Warehouse(new Shop(new Trash(null)));
        control = new ControlQuality();
    }

    /*
    Тест проводился 15.10.2023
     */

    @Disabled
    @Test
    public void pushLemonsInWarehouseSuccess() {
        Food product = new Food("Lemons", LocalDate.of(2023, 12, 5), LocalDate.of(2023, 10, 12), 60, 0);
        control.pushInStore(product, store);
        assertThat(store.getProducts().contains(product)).isTrue();
    }

    @Disabled
    @Test
    public void pushCakeInWarehouseFailes() {
        Food product = new Food("Cake", LocalDate.of(2023, 10, 25), LocalDate.of(2023, 10, 4), 400, 0);
        control.pushInStore(product, store);
        assertThat(store.getProducts().contains(product)).isFalse();
    }
}