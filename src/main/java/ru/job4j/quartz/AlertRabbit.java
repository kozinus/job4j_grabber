package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {
    public static void main(String[] args) {
        try {
            List<Long> store = new ArrayList<>();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("store", store);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(5)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
            System.out.println(store);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class Rabbit implements Job {

        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) {
            Properties cfg = new Properties();
            try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
                cfg.load(in);
                Class.forName(cfg.getProperty("driver_class"));
                String url = cfg.getProperty("url");
                String login = cfg.getProperty("username");
                String password = cfg.getProperty("password");
                Connection connection = DriverManager.getConnection(url, login, password);
                Statement statement = connection.createStatement();
                System.out.println("Rabbit runs here ...");
                List<Long> store = (List<Long>) context.getJobDetail().getJobDataMap().get("store");
                Long created = System.currentTimeMillis();
                store.add(created);
                statement.execute(String.format("INSERT INTO rabbit(created_date) VALUES (%d)", created));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}