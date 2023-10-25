package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingTest {
    private Parking parking;
    private Car car1;
    private Car car2;
    private Car truck1;
    private Car truck2;

    @Disabled
    @Test
    public void add2Cars() {
        parking.add(car1);
        parking.add(car2);
        assertThat(parking.getAllCars()).contains(car1, car2);
    }

    @Disabled
    @Test
    public void addCarAndTruck() {
        parking.add(car1);
        parking.add(truck1);
        assertThat(parking.getAllCars()).contains(car1, truck1);
    }

    @Disabled
    @Test
    public void add2CarsAnd2Trucks() {
        parking.add(car1);
        parking.add(car2);
        parking.add(truck1);
        assertThat(parking.add(truck2)).isFalse();
    }

    @Disabled
    @Test
    public void addCarAnd2Trucks() {
        parking.add(car2);
        parking.add(truck1);
        assertThat(parking.add(truck2)).isTrue();
    }
}