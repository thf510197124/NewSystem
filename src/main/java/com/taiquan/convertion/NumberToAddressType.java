package com.taiquan.convertion;

import com.taiquan.domain.customerEnums.AddressType;
import org.springframework.core.convert.converter.Converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberToAddressType implements Converter<String,AddressType> {
    @Override
    public AddressType convert(String s) {
       /* System.out.println("ConversionService NumberToAddressType is using---------------------------------------------------------");*/
        int indal=0;
        try{

            indal = Integer.valueOf(s);
            if(indal <0 || indal > AddressType.values().length){
                throw new IndexOutOfBoundsException();
            }
            /*System.out.println("--------------------------------------------------------");
            System.out.println("input is a number");*/
            return AddressType.values()[indal];
        }catch (Exception e){
            try{
                Pattern pattern = Pattern.compile("[0-9]{1,2}");
                Matcher matcher = pattern.matcher(s);
                indal = Integer.valueOf(matcher.group());
                /*System.out.println("--------------------------------------------------------");
                System.out.println("input is a number");*/
                return AddressType.values()[indal];
            }catch (Exception e1){
                return AddressType.values()[0];
            }
        }
    }
}
