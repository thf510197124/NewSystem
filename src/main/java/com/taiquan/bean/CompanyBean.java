package com.taiquan.bean;

import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.customer.*;
import com.taiquan.domain.customerEnums.AddressType;
import com.taiquan.domain.customerEnums.CapitalType;
import com.taiquan.domain.customerEnums.CustomerType;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CompanyBean extends BaseDomain{
    private String       companyName;
    private String       owner;
    //在后台将String的phone转换成phoneNumber
    private PhoneNumber  phoneNumber;
    private float        capital;
    @Enumerated(EnumType.ORDINAL)
    private CapitalType  capitalType;
    private Date         establishedDate;
    private String       web;
    private String       businesses;
    //添加关于客户的说明，已经联系电话的情况等
    private String       others;
    private Address address;
    private CustomerType customerType;
    private Date nextDate;

    public Company toCompany(CompanyBean cb){
        Company company = new Company();
        company.setCapital(cb.getCapital());
        if (cb.getAddress()!=null) {
            Address address = cb.getAddress();
            //在部分添加失败的情形中，company的address如果为Address的话，会出现无法转化的问题，不知道是不是地址转化不成功的情形，明天再测试一下！
            address.setCompany(company);
            address.setAddressType(AddressType.注册地址);
            Set<Address> addresses = new HashSet<>();
            addresses.add(address);
            company.setAddresses(addresses);
        }
        company.setBusinesses(cb.getBusinesses().equals("") ? null :
                (cb.getBusinesses().trim().length() > 255 ? cb.getBusinesses().trim().substring(0,254) : cb.getBusinesses().trim()));
        company.setCapitalType(cb.getCapitalType());
        company.setCompanyName(cb.getCompanyName().equals("")?null:cb.getCompanyName().trim());
        company.setEstablishedDate(cb.getEstablishedDate());
        company.setOthers(cb.getOthers().equals("")?null:cb.getOthers().trim());
        company.setOwner(cb.getOwner().equals("")?null:cb.getOwner().trim());
        company.setWeb(cb.getWeb().equals("")?null:cb.getWeb().trim());
        company.setPhoneNumber(cb.getPhoneNumber());
        return company;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }



    public float getCapital() {
        return capital;
    }

    public void setCapital(float capital) {
        this.capital = capital;
    }

    public CapitalType getCapitalType() {
        return capitalType;
    }

    public void setCapitalType(CapitalType capitalType) {
        this.capitalType = capitalType;
    }

    public Date getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(Date establishedDate) {
        this.establishedDate = establishedDate;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getBusinesses() {
        return businesses;
    }

    public void setBusinesses(String businesses) {
        this.businesses = businesses;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Date getNextDate() {
        return nextDate;
    }

    public void setNextDate(Date nextDate) {
        this.nextDate = nextDate;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
