package ru.job4j.ood.srp.format;

import ru.job4j.ood.srp.datetime.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.util.List;

public interface Format {
    default String generateInFormat(List<Employee> employee, DateTimeParser dateTimeParser) {
        return null;
    }
}
