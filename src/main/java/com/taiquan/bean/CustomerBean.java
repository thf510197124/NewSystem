package com.taiquan.bean;

import com.taiquan.domain.customer.Customer;

public class CustomerBean {
    private int customerId;
    private String companyName;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public static CustomerBean getBeanByCustomer(Customer customer){
        CustomerBean customerBean = new CustomerBean();
        customerBean.setCustomerId(customer.getCustomerId());
        customerBean.setCompanyName(customer.getCompany().getCompanyName());
        return customerBean;
    }
}
