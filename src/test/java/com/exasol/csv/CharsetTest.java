package com.exasol.csv;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CharsetTest {

    @Test
    void test_charsetUtf8MustBePresent() {
        final Optional<Charset> optionalUtf8Charset = Charset.availableCharsets()
                .values()
                .stream()
                .filter(c -> c.displayName().equals("UTF-8"))
                .findAny();

        assertTrue(optionalUtf8Charset.isPresent());

    }
}
