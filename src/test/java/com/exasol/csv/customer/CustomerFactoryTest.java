package com.exasol.csv.customer;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.exasol.csv.customer.CustomerFactory.MAX_AGE;
import static org.assertj.core.api.Assertions.assertThat;

class CustomerFactoryTest {

    public static final int CUSTOMER_ID = 1;

    @Test
    void test_createCustomer_fieldShouldMatch() {
        final CustomerFactory underTest = new CustomerFactory();
        final Customer customer = underTest.createCustomer(CUSTOMER_ID);

        assertThat(customer.getId()).isEqualTo(CUSTOMER_ID);
        assertThat(customer.getAge()).isLessThan(MAX_AGE);
    }

    @Test
    void test_createCustomerList_sizeShouldMatch() {
        final CustomerFactory underTest = new CustomerFactory();
        final int expectedCount = 50;
        final List<Customer> customerList = underTest.createCustomerList(expectedCount);
        assertThat(customerList).hasSize(expectedCount);
        assertThat(customerList.get(0).getId()).isEqualTo(1);
    }

    @Test
    void test_createCustomerList_idsShouldStartWithOne() {
        final CustomerFactory underTest = new CustomerFactory();
        final int expectedCount = 10;

        final List<Customer> customerList = underTest.createCustomerList(expectedCount);

        final int firstIndex = 0;
        assertThat(customerList.get(firstIndex).getId()).isEqualTo(1);

        final int lastIndex = expectedCount - 1;
        assertThat(customerList.get(lastIndex).getId()).isEqualTo(expectedCount);
    }
}