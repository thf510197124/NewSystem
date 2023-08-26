package com.taiquan.domain.order.enums;

import java.io.Serializable;

public enum GoodType implements Serializable {

    BANJUAN("不锈钢板"),
    GUAN("不锈钢管"),
    XING("不锈钢型材"),
    LING("不锈钢零部件"),
    JIAGONG("加工"),
    OTHER("其他");

    public String getName() {
        return name;
    }

    private String name;

    private GoodType(String name) {
        this.name = name;
    }

}
