package ru.job4j.ood.srp;

import ru.job4j.gc.Person;

import java.util.List;

public interface Example {

    List<Person> personsGenerator(int size);

    void print();

    void sort();
}
