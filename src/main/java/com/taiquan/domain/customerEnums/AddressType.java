package com.taiquan.domain.customerEnums;

import java.io.Serializable;

public enum AddressType implements Serializable{
    注册地址,
    办公地址,
    工厂地址,
    废弃地址,
    收货地址;

    public static AddressType valueOf(int index){
        if(index<0 || index > AddressType.values().length){
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        return values()[index];
    }
}
