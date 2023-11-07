package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Parking {
    boolean add(Car car);

    boolean remove(Car car);

    List<Car> getAllCars();
}
