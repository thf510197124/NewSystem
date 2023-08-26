package com.taiquan.convertion;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.taiquan.utils.PrintUtil.println;

public class StringAndDateFormatter implements Formatter<Date> {
    /***
     * @param s，解析如下格式：yyyy年(M)M月(d)D日，yyyy-(0)M-(D)D日
     * @param locale
     * @return
     * @throws ParseException
     */
    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        String numerOnly = PhoneNumbersFormat.numberOnly(s);
        if (numerOnly.length() == 8){
            calendar.set(Integer.valueOf(numerOnly.substring(0,4)),Integer.valueOf(numerOnly.substring(4,6)) -1,Integer.valueOf(numerOnly.substring(6,8)));
        }
        if (numerOnly.length() == 6){
            calendar.set(Integer.valueOf(numerOnly.substring(0,4)),
                    Integer.valueOf(numerOnly.substring(4,5))-1,
                    Integer.valueOf(numerOnly.substring(5,6)));
        }
        if (numerOnly.length() == 7){
            String nesStr = parseStringForDate(s);
            int indexYear = nesStr.indexOf("-");
            int indexMonth = nesStr.lastIndexOf("-");
            calendar.set(Integer.valueOf(nesStr.substring(0,indexYear)),
                    Integer.valueOf(nesStr.substring(indexYear + 1,indexMonth)) -1,
                    Integer.valueOf(nesStr.substring(indexMonth+1)));
        }
        return calendar.getTime();
    }


    @Override
    public String print(Date date, Locale locale) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    private static String parseStringForDate(String s){
        String regex = "[0-9]";
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile(regex);
        for (int i=0;i<s.length();i++){
            Matcher matcher = p.matcher(s.substring(i,i+1));
            //如果是数字，那就复制过去，如果是文字之类的，则替换成 -；
            if (matcher.find()){
                sb.append(s.substring(i,i+1));
            }else{
                sb.append("-");
            }
        }
        String formatDateString = sb.toString();
        if (formatDateString.endsWith("-")){
            return formatDateString.substring(0,formatDateString.length() -1);
        }
        return formatDateString;
    }
    public static void main(String[] args) throws ParseException {
        String dates = "1998年12月5日";
        StringAndDateFormatter converter = new StringAndDateFormatter();
        Date date = converter.parse(dates,Locale.CHINA);
        println(date.getYear());
        println(date.getTime());
        println(converter.parse(dates,Locale.CHINA).toLocaleString());
        println(converter.print(date,Locale.CHINA));
    }
}
