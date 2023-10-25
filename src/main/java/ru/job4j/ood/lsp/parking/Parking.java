package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;

public interface Parking {
    boolean add(Car car);

    Car remove(Car car);

    ArrayList<Car> getAllCars();
}
