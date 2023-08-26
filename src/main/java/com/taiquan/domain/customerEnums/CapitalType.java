package com.taiquan.domain.customerEnums;

import java.io.Serializable;

public enum CapitalType implements Serializable{
    万元(1.0000f),
    万美元(6.9459f),
    万欧元(7.8882f),
    万日元(0.06158f),
    万韩元(0.006144f),
    万港币(0.8876f),
    万加元(5.2501f),
    万澳元(5.0198f),
    万英镑(8.9095f),
    其他(1.000f);
    private  float currentcy;
    private CapitalType(float currency){
        this.currentcy = currency;
    }

    public float getCurrentcy() {
        return currentcy;
    }

    public void setCurrentcy(float currentcy) {
        this.currentcy = currentcy;
    }
    /*public CapitalType valueOf(String name){
        for (CapitalType capitalType : CapitalType.values()){
            //if (name.equals(CapitalType.))
        }
        return CapitalType.万英镑;
    }*/
    public static void main(String[] name){
        System.out.println(CapitalType.valueOf("元").currentcy);
    }
}
