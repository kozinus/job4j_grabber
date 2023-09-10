package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class TemplateTest {

    @Disabled
    @Test
    public void unmatchedKey() {
        Generator template = new Template();
        String text = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Kirill");
        assertThatThrownBy(() -> template.produce(text, map)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Disabled
    @Test
    public void extraKey() {
        Generator template = new Template();
        String text = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Kirill");
        map.put("subject", "they");
        map.put("extra", "third");
        assertThatThrownBy(() -> template.produce(text, map)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Disabled
    @Test
    public void doubleKeyMap() {
        Generator template = new Template();
        String text = "I am a ${name}, Who are ${subject}? ";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Kirill");
        map.put("subject", "they");
        assertThat(template.produce(text, map))
                .isEqualTo("I am a Kirill, Who are they? ");
    }
}