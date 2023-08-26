package com.taiquan.convertion;

import com.taiquan.domain.customerEnums.CustomerType;
import org.springframework.core.convert.converter.Converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberToCustomerType implements Converter<String,CustomerType> {
    @Override
    public CustomerType convert(String s) {
        int indal=0;
        try{

            indal = Integer.valueOf(s);
            if(indal <0 || indal > CustomerType.values().length){
                throw new IndexOutOfBoundsException();
            }
            return CustomerType.values()[indal];
        }catch (Exception e){
            try{
                Pattern pattern = Pattern.compile("[0-9]{1,2}");
                Matcher matcher = pattern.matcher(s);
                indal = Integer.valueOf(matcher.group());
                return CustomerType.values()[indal];
            }catch (Exception e1){
                return CustomerType.values()[0];
            }
        }
    }
}
