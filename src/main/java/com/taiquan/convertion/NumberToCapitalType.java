package com.taiquan.convertion;

import com.taiquan.domain.customerEnums.CapitalType;
import org.springframework.core.convert.converter.Converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberToCapitalType implements Converter<String,CapitalType> {
    @Override
    public CapitalType convert(String s) {
        int indal=0;
        try{

            indal = Integer.valueOf(s);
            if(indal <0 || indal > CapitalType.values().length){
                throw new IndexOutOfBoundsException();
            }
            return CapitalType.values()[indal];
        }catch (Exception e){
            try{
                Pattern pattern = Pattern.compile("[0-9]{1,2}");
                Matcher matcher = pattern.matcher(s);
                indal = Integer.valueOf(matcher.group());
                return CapitalType.values()[indal];
            }catch (Exception e1){
                return CapitalType.values()[0];
            }
        }
    }
}
