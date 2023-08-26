package com.taiquan.convertion;

import com.taiquan.domain.order.enums.goodDetailType.GoodDetailType;
import org.springframework.core.convert.converter.Converter;

public class StringToGoodDetailType implements Converter<String, GoodDetailType> {
    @Override
    public GoodDetailType convert(String s) {
        for (GoodDetailType goodDetailType:GoodDetailType.values()){
            if (goodDetailType.name().equals(s.trim())){
                return goodDetailType;
            }
        }
        return null;
    }
}
