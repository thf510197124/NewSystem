package com.taiquan.bean;

import java.util.Date;
import java.util.List;

/***
 * 为了批量上传客户设计
 */
public class DataCompany {
    public String name;
    public String companyId;
    public String province;
    public String city;
    public String region;
    public String owner;
    public float money;
    public String moneyUnit;
    public Date esDate;
    public String sector;
    public String business;
    public List<String> phoneNumber;
    public String address;
    public String email;
    public String webAdd;

    public DataCompany(){}
    public DataCompany(String name, String companyId, String province, String city, String region, String owner,
                       float money, String moneyUnit, Date esDate, String sector, String business, List<String> phoneNUmber,
                       String address, String email, String webAdd) {
        this.name = name;
        this.companyId = companyId;
        this.province = province;
        this.city = city;
        this.region = region;
        this.owner = owner;
        this.money = money;
        this.moneyUnit = moneyUnit;
        this.esDate = esDate;
        this.sector = sector;
        this.business = business;
        this.phoneNumber = phoneNUmber;
        this.address = address;
        this.email = email;
        this.webAdd = webAdd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public void setMoneyUnit(String moneyUnit) {
        this.moneyUnit = moneyUnit;
    }

    public void setEsDate(Date esDate) {
        this.esDate = esDate;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public void setPhoneNumber(List<String> phoneNUmber) {
        this.phoneNumber = phoneNUmber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setWebAdd(String webAdd) {
        this.webAdd = webAdd;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", companyId='" + companyId + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", owner='" + owner + '\'' +
                ", money=" + money +
                ", moneyUnit='" + moneyUnit + '\'' +
                ", esDate=" + esDate +
                ", sector='" + sector + '\'' +
                ", business='" + business + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", webAdd='" + webAdd + '\'' +
                '}';
    }
}
