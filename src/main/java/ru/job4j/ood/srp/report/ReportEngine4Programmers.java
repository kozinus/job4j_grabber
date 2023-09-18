package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.format.Format;
import ru.job4j.ood.srp.datetime.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportEngine4Programmers implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final Format formateer;

    public ReportEngine4Programmers(Store store, DateTimeParser<Calendar> dateTimeParser, Format formateer) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.formateer = formateer;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return formateer.generateInFormat(store.findBy(filter), dateTimeParser);
    }
}