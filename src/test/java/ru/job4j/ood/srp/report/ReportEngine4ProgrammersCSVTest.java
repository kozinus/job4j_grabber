package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.datetime.DateTimeParser;
import ru.job4j.ood.srp.datetime.ReportDateTimeParser;
import ru.job4j.ood.srp.format.CSVFormat;
import ru.job4j.ood.srp.format.Format;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportEngine4ProgrammersCSVTest {

    @Test
    public void whenMultiplesGeneratedCSV() {
        MemStore store = new MemStore();
        Format format = new CSVFormat();
        Calendar now = Calendar.getInstance();
        Calendar hire = new Calendar.Builder().setCalendarType("iso8601")
                .setDate(2020, 4, 13)
                .setTimeOfDay(14, 12, 55).build();
        Calendar fire = new Calendar.Builder().setCalendarType("iso8601")
                .setDate(2022, 12, 11)
                .setTimeOfDay(11, 12, 55).build();
        store.add(new Employee("Ivan", hire, fire, 100));
        store.add(new Employee("Maks", hire, now, 120));
        store.add(new Employee("Ilya", now, now, 110));
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Report engine = new ReportEngine4Programmers(store, parser, format);
        StringBuilder expect = new StringBuilder().append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator());
        for (Employee worker : store.findBy(x -> true)) {
            expect.append(worker.getName()).append(";")
                    .append(parser.parse(worker.getHired())).append(";")
                    .append(parser.parse(worker.getFired())).append(";")
                    .append(worker.getSalary())
                    .append(System.lineSeparator());
        }
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}