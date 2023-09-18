package ru.job4j.ood.srp.format;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.ood.srp.datetime.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;

import java.util.List;

public class JSONFormat implements Format {
    @Override
    public String generateInFormat(List<Employee> employees, DateTimeParser dtp) {
        JSONArray jsonArray = new JSONArray();

        for (Employee e : employees) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", dtp.parse(e.getHired()));
            jsonObject.put("hired", dtp.parse(e.getFired()));
            jsonObject.put("salary", e.getSalary());
            jsonArray.put(jsonObject);
        }
        System.out.println(jsonArray.toString());
        return jsonArray.toString();
    }
}
