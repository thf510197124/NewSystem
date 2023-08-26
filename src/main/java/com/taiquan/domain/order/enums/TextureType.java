package com.taiquan.domain.order.enums;

import com.taiquan.exception.NoSuchEnumException;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public enum  TextureType  implements Serializable {
    T201("201",7.93f),
    T201J2("201J2",7.93f),
    T202("202",7.93f),
    T304("304",7.93f),
    T301("301",7.93f),
    T302("302",7.93f),
    T30408("30408",7.93f),
    T30403("30403",7.93f),
    T304L("304L",7.93f),
    T304H("304H",7.93f),
    T304D("304D",7.93f),
    T304J1("304J1",7.93f),
    T316("316",7.98f),
    T316L("316L",7.98f),
    T31603("31603",7.98f),
    T316Ti("316Ti",7.98f),
    T316N("316N",7.98f),
    T321("321",7.93f),
    T317L("317L",8.05f),
    T347("347",7.98f),
    T347H("347H",7.98f),
    T309S("309S",8.03f),
    T310S("310S",8.03f),
    T2205("2205",8.0f),
    T2507("2507",8.05f),
    T444("444",7.75f),
    T410("410",7.75f),
    T410S("410S",7.75f),
    T409L("409L",7.75f),
    T420("420",7.75f),
    T430("430",7.75f),
    T630("630",7.85f),
    T904L("904L",8.00f),
    Others("其他",0f);

    private String name;
    private float density;
    private TextureType(String name,float density){
        this.density = density;
        this.name=name;
    }
    public static TextureType getTexture(String name) throws NoSuchEnumException {
        if (name != null && !name.equals("")) {
            for (TextureType textureType : TextureType.values()) {
                if (textureType.getName().equals(name.trim())) {
                    return textureType;
                }
            }
            throw new NoSuchEnumException("您输入的材质不在材质没找到");
        }else{
            return null;
        }

    }
    public float getDensity(String name) throws NoSuchEnumException{
        if (name != null && !name.equals("")) {
            for (TextureType textureType : TextureType.values()) {
                if (textureType.getName().equals(name.trim())) {
                    return textureType.getDensity();
                }
            }
            throw new NoSuchEnumException("您输入的材质不在材质没找到");
        }else{
            throw new NoSuchEnumException("材质不能为空");
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    @Override
    public String toString() {
        return name;
    }
}
