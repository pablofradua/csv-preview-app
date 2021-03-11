package com.exasol.csv.customer;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class CustomerFactory {

    static final String[] FIRST_NAMES = new String[]{
            "Anderson",
            "Mark",
            "Mike",
            "Angela",
            "Katherine",
            "Stephen",
            "Adam",
            "Marco",
            "Daniela"
    };

    static final String[] LAST_NAMES = new String[]{
            "Berger",
            "Lowe",
            "Cantrell",
            "White",
            "Müller",
            "Stevens",
            "Jagger",
            "Schmitz",
            "Quinn"
    };

    static final int MAX_AGE = 90;

    private final Random random = new Random();

    List<Customer> createCustomerList(final int count) {
        final int endOfRange = count + 1;
        return IntStream.range(1, endOfRange)
                .boxed()
                .map(this::createCustomer)
                .collect(Collectors.toList());
    }

    Customer createCustomer(final int id) {
        final int age = random.nextInt(MAX_AGE);
        final String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        final String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

        return Customer.builder()
                .age(age)
                .firstName(firstName)
                .lastName(lastName)
                .id(id)
                .build();
    }
}
