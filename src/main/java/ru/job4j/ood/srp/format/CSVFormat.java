package ru.job4j.ood.srp.format;

import ru.job4j.ood.srp.datetime.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.util.List;

public class CSVFormat implements Format {

    public String generateInFormat(List<Employee> employees, DateTimeParser dtp) {
        StringBuilder text = new StringBuilder();
        text.append("Name;Hired;Fired;Salary")
                .append(System.lineSeparator());
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(dtp.parse(employee.getHired())).append(";")
                    .append(dtp.parse(employee.getFired())).append(";")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
