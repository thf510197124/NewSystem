package com.taiquan.convertion;

import com.taiquan.domain.customerEnums.PhoneType;
import org.springframework.core.convert.converter.Converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberToPhoneType implements Converter<String,PhoneType> {
    @Override
    public PhoneType convert(String s) {
        int indal=0;
        try{

            indal = Integer.valueOf(s);
            if(indal <0 || indal > PhoneType.values().length){
                throw new IndexOutOfBoundsException();
            }
            return PhoneType.values()[indal];
        }catch (Exception e){
            try{
                Pattern pattern = Pattern.compile("[0-9]{1,2}");
                Matcher matcher = pattern.matcher(s);
                indal = Integer.valueOf(matcher.group());
                return PhoneType.values()[indal];
            }catch (Exception e1){
                return PhoneType.values()[0];
            }
        }
    }
}
