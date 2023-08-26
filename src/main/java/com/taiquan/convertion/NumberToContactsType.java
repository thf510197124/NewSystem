package com.taiquan.convertion;


import com.taiquan.domain.customerEnums.ContactsType;
import org.springframework.core.convert.converter.Converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberToContactsType implements Converter<String, ContactsType> {
    @Override
    public ContactsType convert(String s) {
        int indal=0;
        try{

            indal = Integer.valueOf(s);
            if(indal <0 || indal > ContactsType.values().length){
                throw new IndexOutOfBoundsException();
            }
            return ContactsType.values()[indal];
        }catch (Exception e){
            try{
                Pattern pattern = Pattern.compile("[0-9]{1,2}");
                Matcher matcher = pattern.matcher(s);
                indal = Integer.valueOf(matcher.group());
                return ContactsType.values()[indal];
            }catch (Exception e1){
                return ContactsType.values()[0];
            }
        }
    }
}
