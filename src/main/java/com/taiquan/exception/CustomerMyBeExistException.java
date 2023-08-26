package com.taiquan.exception;

import com.taiquan.domain.customer.Customer;

import java.util.List;

public class CustomerMyBeExistException extends Exception {
    List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public CustomerMyBeExistException(List<Customer> customers){
        super("客户可能已经存在，类似的客户");
        this.customers = customers;
    }
}
