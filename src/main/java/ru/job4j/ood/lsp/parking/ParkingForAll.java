package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ParkingForAll implements Parking {
    private final ArrayList<Car> autos = new ArrayList<>();
    private final ArrayList<Car> trucks = new ArrayList<>();
    private final int autoSpaces;
    private final int truckSpaces;

    public ParkingForAll(int autoSpaces, int truckSpaces) {
        this.autoSpaces = autoSpaces;
        this.truckSpaces = truckSpaces;
    }

    @Override
    public boolean add(Car car) {
        Predicate<Car> inTruckAdd = x -> {
            boolean flag = false;
            if (trucks.size() < truckSpaces) {
                flag = true;
                trucks.add(x);
            }
            return flag;
        };

        Predicate<Car> inAutoAdd = x -> {
            boolean flag = false;
            if (autos.size() + x.getSize() <= autoSpaces) {
                flag = true;
                for (int i = 0; i < x.getSize(); i++) {
                    autos.add(x);
                }
            }
            return flag;
        };

        int size = car.getSize();
        boolean flag = !(autos.contains(car) || trucks.contains(car));
        if (flag) {
            flag = (size > 1 && inTruckAdd.test(car)) || inAutoAdd.test(car);
        }
        return flag;
    }

    @Override
    public boolean remove(Car car) {
        boolean flag = trucks.remove(car);
        while (autos.remove(car)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> out = new ArrayList<>(autos);
        out.addAll(trucks);
        return out;
    }
}
