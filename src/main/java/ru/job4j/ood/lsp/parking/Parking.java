package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;

public interface Parking {
    void add();

    Car remove();

    ArrayList<Car> getAllCars();
}
