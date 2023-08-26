package com.taiquan.exception;

import com.taiquan.domain.customer.Customer;

public class CustomerHasExistException extends Exception {
    Customer customer;
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CustomerHasExistException(Customer customer){
        super("客户已存在");
        this.customer=customer;
    }
}
