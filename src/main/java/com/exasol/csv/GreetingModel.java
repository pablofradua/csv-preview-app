package com.exasol.csv;

import lombok.Getter;

import javax.enterprise.inject.Model;

/**
 * Simple Hello World backing bean
 */
@Model
public class GreetingModel {

    @Getter
    private String greeting = "Hello World";


}
