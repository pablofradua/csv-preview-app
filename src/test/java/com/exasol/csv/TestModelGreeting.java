package com.exasol.csv;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestModelGreeting {

    /**
     * Make sure that JUnit works...
     */
    @Test
    void testGreeting() {
        final GreetingModel greetingModel = new GreetingModel();
        assertEquals("Hello World", greetingModel.getGreeting());
    }

    @Test
    void utf8ShouldBePresent() {
        final Optional<Charset> optionalUtf8Charset = Charset.availableCharsets()
                .values()
                .stream()
                .filter(c -> c.displayName().equals("UTF-8"))
                .findAny();

        assertTrue(optionalUtf8Charset.isPresent());

    }
}