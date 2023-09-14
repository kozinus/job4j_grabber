package ru.job4j.ood.srp;

import ru.job4j.gc.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExampleClass implements Example {
    List<Person> people = new ArrayList<>();

    @Override
    public List<Person> personsGenerator(int size) {
        List<Person> generated = new ArrayList<>();
        Random random = new Random();
        generated.add(new Person(random.nextInt(), "Person"));
        return generated;
    }

    @Override
    public void print() {
        System.out.println(people);
    }

    @Override
    public void sort() {
    }
}
