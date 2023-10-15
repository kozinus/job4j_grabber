package ru.job4j.ood.lsp.foodstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ControlQualityTest {
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

    @Test
    public void pushColaInShop() {
        Food cola = new Food("Cola", LocalDate.of(2024, 5, 21), LocalDate.of(2023, 4, 22), 55, 0);
        control.pushInStore(cola, store);
        assertThat(store.shift().getProducts().contains(cola)).isTrue();
    }

    @Test
    public void pushApplesInTrash() {
        Food apples = new Food("Apples", LocalDate.of(2023, 10, 10), LocalDate.of(2023, 9, 5), 120, 0);
        control.pushInStore(apples, store);
        assertThat(store.shift().shift().getProducts().contains(apples)).isTrue();
    }

    @Test
    public void pushTomatoesInShopWithDiscount() {
        Food tomatoes = new Food("Tomatoes", LocalDate.of(2023, 10, 18), LocalDate.of(2023, 9, 5), 200, 0);
        control.pushInStore(tomatoes, store);
        assertThat(store.shift().getProducts().get(0).getDiscount()).isEqualTo(20);
    }

    @Test
    public void pushLemonsInWarehouse() {
        Food lemons = new Food("Lemons", LocalDate.of(2023, 12, 5), LocalDate.of(2023, 10, 12), 60, 0);
        control.pushInStore(lemons, store);
        assertThat(store.getProducts().contains(lemons)).isTrue();
    }
}