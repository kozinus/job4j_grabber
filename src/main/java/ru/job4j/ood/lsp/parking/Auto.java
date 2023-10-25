package ru.job4j.ood.lsp.parking;

public class Auto implements Car {
    private final String name;

    public Auto(String name) {
        this.name = name;
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public String toString() {
        return "Auto{" + "name='"
                + name + '\''
                + ", size=1"
                + '}';
    }
}
