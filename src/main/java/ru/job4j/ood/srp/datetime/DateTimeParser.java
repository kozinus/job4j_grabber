package ru.job4j.ood.srp.datetime;

public interface DateTimeParser<T> {
    String parse(T t);
}