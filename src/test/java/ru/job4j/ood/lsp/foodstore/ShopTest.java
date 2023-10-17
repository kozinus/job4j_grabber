package ru.job4j.ood.lsp.foodstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ShopTest {
    private Store store;
    private ControlQuality control;

    @BeforeEach
    void init() {
        store = new Warehouse(new Shop(new Trash(null)));
        control = new ControlQuality();
    }

    @Test
    public void pushColaInShop() {
        Food cola = new Food("Cola", LocalDate.of(2024, 5, 21), LocalDate.of(2023, 4, 22), 55, 0);
        control.pushInStore(cola, store);
        assertThat(store.shift().getProducts().contains(cola)).isTrue();
    }

    @Test
    public void pushTomatoesInShopWithDiscount() {
        Food product = new Food("Tomatoes", LocalDate.of(2023, 10, 18), LocalDate.of(2023, 9, 5), 200, 0);
        control.pushInStore(product, store);
        assertThat(store.shift().getProducts().contains(product)).isTrue();
        assertThat(store.shift().getProducts().get(0).getDiscount()).isEqualTo(20);
    }
}