package com.taiquan.convertion;

import com.taiquan.domain.order.enums.TextureType;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Set;

public class TextureSetFormatter implements Formatter<Set<TextureType>> {
    @Override
    public Set<TextureType> parse(String s, Locale locale) throws ParseException {
        return null;
    }

    @Override
    public String print(Set<TextureType> set, Locale locale) {
        if (set.size()<1)
            return null;
        StringBuilder sb = new StringBuilder();
        for (TextureType type : set ){
            sb.append(type.getName()+",");
        }
        String sbString = sb.toString();
        if (sbString.length()<2)
            return null;
        return sbString.substring(0,sbString.length()-2);
    }
}
