package com.exasol.csv;

import lombok.Getter;

import javax.enterprise.inject.Model;

/**
 * Simple Hello World Model
 */
@Model
public class GreetingModel {

    @Getter
    private String greeting = "Hello World";


}
