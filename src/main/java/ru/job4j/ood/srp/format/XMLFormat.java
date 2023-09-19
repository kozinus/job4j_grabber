package ru.job4j.ood.srp.format;

import org.xembly.Directives;
import org.xembly.Xembler;
import ru.job4j.ood.srp.datetime.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.util.List;

public class XMLFormat implements Format {

    @Override
    public String generateInFormat(List<Employee> employees, DateTimeParser dtp) {
        Directives directives = new Directives();
        directives.add("employees");
        for (Employee e : employees) {
            directives.add("employee")
            .add("name").set(e.getName()).up()
            .add("hired").set(dtp.parse(e.getHired())).up()
            .add("fired").set(dtp.parse(e.getFired())).up()
                    .add("salary").set(String.valueOf(e.getSalary())).up().up();
        }
        return new Xembler(directives).xmlQuietly();
    }
}
