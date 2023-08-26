package com.taiquan.domain.customerEnums;

import java.io.Serializable;

public enum PhoneType  implements Serializable {
    总机,
    手机,
    家庭,
    工作,
    固话,
    传真,
    QQ,
    微信,
    未联系的电话,
    无用电话,
    联系中,
    空号;

    @Override
    public String toString() {
        return super.toString();
    }
    public static PhoneType getPhoneType(int index){
        return PhoneType.values()[index];
    }
}
