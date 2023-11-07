package ru.job4j.ood.lsp.parking;

public class Truck implements Car {
    private final String name;
    private final int size;

    public Truck(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Truck{" + "name='"
                + name + '\''
                + ", size=" + size
                + '}';
    }
}
