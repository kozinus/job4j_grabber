package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.datetime.DateTimeParser;
import ru.job4j.ood.srp.datetime.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportEngine4AccountancyTest {

    @Test
    public void whenMultiplesGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        InMemoryCurrencyConverter converter = new InMemoryCurrencyConverter();
        Calendar hire = new Calendar.Builder().setCalendarType("iso8601")
                .setDate(2020, 4, 13)
                .setTimeOfDay(14, 12, 55).build();
        Calendar fire = new Calendar.Builder().setCalendarType("iso8601")
                .setDate(2022, 12, 11)
                .setTimeOfDay(11, 12, 55).build();
        store.add(new Employee("Ivan", hire, fire, 100));
        store.add(new Employee("Maks", hire, now, 120));
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new ReportEngine4Accountancy(store, parser);
        StringBuilder expect = new StringBuilder().append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee worker : store.findBy(x -> true)) {
            expect.append(worker.getName()).append(" ")
                    .append(parser.parse(worker.getHired())).append(" ")
                    .append(parser.parse(worker.getFired())).append(" ")
                    .append(worker.getSalary()).append(" ")
                    .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.USD)).append(" ")
                    .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.EUR))
                    .append(System.lineSeparator());
        }
        System.out.println(expect);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}