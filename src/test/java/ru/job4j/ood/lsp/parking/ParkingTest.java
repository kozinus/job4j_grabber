package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingTest {
    private Parking parking;
    private Car car1;
    private Car car2;
    private Car truck1;
    private Car truck2;

    @BeforeEach
    public void init() {
        parking = new ParkingForAll(4, 1);
        car1 = new Auto("Audi");
        car2 = new Auto("VW");
        truck1 = new Truck("Gazel'", 2);
        truck2 = new Truck("IDK", 3);
    }

    @Test
    public void add2Cars() {
        parking.add(car1);
        parking.add(car2);
        assertThat(parking.getAllCars()).contains(car1, car2);
    }

    @Test
    public void addCarAndTruck() {
        parking.add(car1);
        parking.add(truck1);
        assertThat(parking.getAllCars()).contains(car1, truck1);
    }

    @Test
    public void add2CarsAnd2Trucks() {
        parking.add(car1);
        parking.add(car2);
        parking.add(truck1);
        assertThat(parking.add(truck2)).isFalse();
    }

    @Test
    public void addCarAnd2Trucks() {
        parking.add(car2);
        parking.add(truck1);
        assertThat(parking.add(truck2)).isTrue();
    }

    @Test
    public void add2CarsAndTruckThenDeleteCarAndAddTruck() {
        parking.add(car1);
        parking.add(car2);
        parking.add(truck1);
        assertThat(parking.remove(car2)).isTrue();
        assertThat(parking.add(truck2)).isTrue();
        assertThat(parking.getAllCars()).contains(car1, truck1, truck2);
    }

    @Test
    public void add2CarsAndTruckThenDeleteTruckAndAddAnother() {
        parking.add(car1);
        parking.add(car2);
        parking.add(truck1);
        assertThat(parking.remove(truck1)).isTrue();
        assertThat(parking.add(truck2)).isTrue();
    }

    @Test
    public void addCarAnd2TrucksThenDeleteTruckAndAddCar() {
        parking.add(car1);
        parking.add(truck1);
        parking.add(truck2);
        assertThat(parking.remove(truck2)).isTrue();
        assertThat(parking.add(car2)).isTrue();
    }
}