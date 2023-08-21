package ru.job4j.grabber.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HabrCareerDateTimeParserTest {

    private static DateTimeParser parser;
    ///private static LocalDateTime dateTime;

    @BeforeAll
    static void parserInit() {
        parser = new HabrCareerDateTimeParser();
    }

    @Test
    void dateWithTimeZoneParse() {
        ///dateTime = LocalDateTime.parse("2023-08-21T14:27:12");
        assertThat(parser.parse("2023-08-21T14:27:12+03:00")).isEqualTo(LocalDateTime.parse("2023-08-21T14:27:12"));
    }

}