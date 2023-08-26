package com.taiquan.domain.order.enums.goodDetailType;

import java.io.Serializable;
import java.util.NoSuchElementException;

public enum GongZiType implements Serializable {
    //六角棒重量 0.0069*S*S 或者 0.8660*密度
    GONG10("10#","100*68*4.5",11.2f),
    GONG12("12.6#","126*74*5",14.2f),
    GONG14("14#","140*80*5.5",16.9f),
    GONG16("16#","160*88*6",20.5f),
    GONG18("18#","180*94*6.5",24.1f),
    GONG20a("20a#","200*100*7",27.9f),
    GONG20b("20b#","200*102*9",31.1f),
    GONG22a("22a#","220*110*7.5",33f),
    GONG22b("22b#","220*112*9.5",36.4f),
    GONG25a("25a#","250*116*8",38.1f),
    GONG25b("25b#","250*118*10",42f),
    GONG28a("28a#","280*122*8.5",43.4f),
    GONG28b("28b#","280*124*10.5",47.9f),
    GONG32a("32a#","320*130*9.5",52.7f),
    GONG32b("32b#","320*132*11.5",57.7f),
    GONG32c("32c#","320*134*13.5",62.8f);

    private String name;
    private String spec;
    private float weight;

    GongZiType(String name, String spec, float weight) {
        this.name = name;
        this.spec = spec;
        this.weight = weight;
    }

    public float getWeight(String name){
        if (name!= null){
            for(GongZiType caogangType : GongZiType.values()){
                if (caogangType.getName().equals(name)){
                    return caogangType.weight;
                }
            }
        }
        throw new NoSuchElementException("输入的工字钢种类不能为空,或者没有这种工字钢");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
