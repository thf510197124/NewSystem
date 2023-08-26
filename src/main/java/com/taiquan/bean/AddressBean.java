package com.taiquan.bean;

import com.taiquan.domain.BaseDomain;
import com.taiquan.domain.customer.Address;
import com.taiquan.domain.customerEnums.AddressType;


public class AddressBean extends BaseDomain{
    private int addressId;
    private int addressType;
    private String     simple;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
    }

    public String getSimple() {
        return simple;
    }

    public void setSimple(String simple) {
        this.simple = simple;
    }

    public static Address toAddress(AddressBean addressBean){
        AddressType addressType = AddressType.values()[addressBean.getAddressType()];
        return new Address(addressType,addressBean.getSimple());
    }
    public static AddressBean toAddressBean(Address address){
        AddressBean addressBean = new AddressBean();
        addressBean.setAddressId(address.getAddressId());
        addressBean.setAddressType(address.getAddressType().ordinal());
        addressBean.setSimple(address.getSimple());
        return addressBean;
    }
    public static AddressType getAddressType(int addressType){
        return AddressType.values()[addressType];
    }
}
