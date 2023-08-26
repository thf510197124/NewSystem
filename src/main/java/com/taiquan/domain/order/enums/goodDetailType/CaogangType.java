package com.taiquan.domain.order.enums.goodDetailType;

import java.io.Serializable;
import java.util.NoSuchElementException;

public enum CaogangType  implements Serializable {
    CAO5("5#","50*37*4.5",5.438f),
    CAO63("6.3#","63*40*4.8",6.634f),
    CAO65("6.5#","65*40*4.3",6.709f),
    CAO8("8#","80*43*5.0",8.045f),
    CAO10("10#","100*48*5.3",10.007f),
    CAO12("12#","120*53*5.5",12.059f),
    CAO12A("12.6#","126*53*5.5",12.319f),
    CAO14A("14#a","140*58*6",14.535f),
    CAO14B("14#b","140*60*8",16.733f),
    CAO16A("16#a","160*63*6.5",17.24f),
    CAO16B("16#b","160*65*8.5",19.752f),
    CAO18A("18#a","180*68*7",20.174f),
    CAO18B("18#B","180*70*9",23f),
    CAO20A("20#a","200*73*7",22.637f),
    CAO20B("20#b","200*75*9",25.777f),
    CAO22A("22#a","220*77*7",24.999f);
    private String name;
    private String guige;
    private float weight;
    private CaogangType(String name,String guige,float weight){
        this.name = name;
        this.guige = guige;
        this.weight=weight;
    }
    public float getWeight(String name){
        if (name!= null){
            for(CaogangType caogangType : CaogangType.values()){
                if (caogangType.getName().equals(name)){
                    return caogangType.weight;
                }
            }
        }
        throw new NoSuchElementException("输入的槽钢种类不能为空,或者没有这种槽钢");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
