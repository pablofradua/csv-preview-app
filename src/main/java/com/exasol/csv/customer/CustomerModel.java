package com.exasol.csv.customer;

import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import java.util.List;

@Model
public class CustomerModel {

    @Getter
    private List<Customer> customerList;

    @PostConstruct
    private void onPostConstruct() {
        customerList = new CustomerFactory().createCustomerList(100);
    }
}
