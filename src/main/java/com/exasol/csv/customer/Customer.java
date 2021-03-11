package com.exasol.csv.customer;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Customer {
    int id;
    @NonNull String firstName;
    @NonNull String lastName;
    int age;
}
