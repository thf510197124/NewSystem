package com.taiquan.convertion;

import com.taiquan.domain.order.enums.goodDetailType.GoodDetailType;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Set;

public class GoodTypeSetFormatter implements Formatter<Set<GoodDetailType>> {
    @Override
    public Set<GoodDetailType> parse(String s, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(Set<GoodDetailType> goodDetailTypes, Locale locale) {
        if (goodDetailTypes.size()<1)
            return null;
        StringBuilder sb = new StringBuilder();
        for (GoodDetailType goodDetailType : goodDetailTypes){
            sb.append(goodDetailType.toString());
        }
        String sbString = sb.toString();
        if (sbString.length()<2)
            return null;
        return sbString.substring(0,sbString.length()-2);
    }
}
