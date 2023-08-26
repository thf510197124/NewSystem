package com.taiquan.convertion;

import org.springframework.core.convert.converter.Converter;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToSQLDate implements Converter<String,Date> {

    @Override
    public Date convert(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date date = sdf.parse(s);
            return new Date(date.getTime());
        } catch (ParseException e) {
            return new Date(2050,12,31);
        }
    }
}
