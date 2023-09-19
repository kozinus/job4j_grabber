package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import org.xembly.Directives;
import org.xembly.Xembler;
import ru.job4j.ood.srp.datetime.DateTimeParser;
import ru.job4j.ood.srp.datetime.ReportDateTimeParser;
import ru.job4j.ood.srp.format.Format;
import ru.job4j.ood.srp.format.XMLFormat;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

public class ReportEngine4ProgrammersXMLTest {

    @Test
    public void whenMultiplesGeneratedXML() {
        MemStore store = new MemStore();
        Format format = new XMLFormat();
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
        Directives directives = new Directives();
        directives.add("employees");

        for (Employee e : store.findBy(x -> true)) {
            directives.add("employee")
                    .add("name").set(e.getName()).up()
                    .add("hired").set(parser.parse(e.getHired())).up()
                    .add("fired").set(parser.parse(e.getFired())).up()
                    .add("salary").set(String.valueOf(e.getSalary())).up().up();
            }
        String expect = new Xembler(directives).xmlQuietly();
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}