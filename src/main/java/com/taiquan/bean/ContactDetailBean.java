package com.taiquan.bean;

import com.taiquan.domain.BaseDomain;

import java.util.Date;

public class ContactDetailBean extends BaseDomain{
    private String contactDetails;
    private int customerType;
    private Date nextDate;

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public int getCustomerType() {
        return customerType;
    }

    public void setCustomerType(int customerType) {
        this.customerType = customerType;
    }

    public Date getNextDate() {
        return nextDate;
    }

    public void setNextDate(Date nextDate) {
        this.nextDate = nextDate;
    }
}
