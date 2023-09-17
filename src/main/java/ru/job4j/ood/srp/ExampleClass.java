package ru.job4j.ood.srp;

import ru.job4j.gc.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExampleClass {
    List<Person> people = new ArrayList<>();

    public List<Person> personsGeneratorRandom(int size) {
        List<Person> generated = new ArrayList<>();
        Random random = new Random();
        generated.add(new Person(random.nextInt(), "Person"));
        return generated;
    }

    public List<Person> personsGeneratorRandomWithFilter(int size) {
        List<Person> generated = new ArrayList<>();
        Random random = new Random();
        Predicate<Person> filter = x -> true;
        generated.add(new Person(random.nextInt(), "Person"));
        return generated.stream().filter(filter).collect(Collectors.toList());
    }
}
