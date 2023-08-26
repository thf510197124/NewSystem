package com.taiquan.convertion;

import com.taiquan.domain.customer.PhoneNumber;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.taiquan.utils.PrintUtil.println;

//用在系统正式使用时
public class PhoneNumbersFormat implements Formatter<PhoneNumber> {
    private static final String[] split = {" ","-","/",","};
    private static final String CELLPHONE = "^[1][3,4,5,7,8][0-9]{9}$";
    private static final String TELPHONE = "^(0[0-9]{2,3})?[0-9]{7,8}$";
    private static final String SPACE = "  ";
    @Override
    public PhoneNumber parse(String source, Locale locale) throws ParseException {
        if (source==null){
            return null;
        }
        source = numberOnly(source);
        source = substrUsless(source);
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumbers(source);
        return phoneNumber;
    }

    @Override
    public String print(PhoneNumber phoneNumber, Locale locale) {
        StringBuilder sb = new StringBuilder();
        String phs = phoneNumber.getNumbers();
        if (isCellPhone(phoneNumber)){
            sb.append(phs.substring(0,3)).append(SPACE);
            sb.append(phs.substring(3,7)).append(SPACE);
            sb.append(phs.substring(7,11));
        }else if (isTelPhone(phoneNumber)){
            if (phs.length() == 7){
                sb.append(phs.substring(0,3)).append(SPACE);
                sb.append(phs.substring(3,7)).append(SPACE);
            }else if(phs.length() ==8){
                sb.append(phs.substring(0,4)).append(SPACE);
                sb.append(phs.substring(4,8)).append(SPACE);
            }else if (phs.startsWith("01") || phs.startsWith("02")){
                sb.append(phs.substring(0,3)).append(SPACE);
                sb.append(phs.substring(3,7)).append(SPACE);
                sb.append(phs.substring(7));
            }else{
                sb.append(phs.substring(0,4)).append(SPACE);
                if (phs.length() == 11){
                    sb.append(phs.substring(4,7)).append(SPACE);
                    sb.append(phs.substring(7,11));
                }else{
                    //println(phs);
                    sb.append(phs.substring(4,8)).append(SPACE);
                    sb.append(phs.substring(8,phs.length()));
                }
            }
        }else{
            return formatString(phoneNumber.getNumbers());
        }
        return sb.toString();
    }

    public static String substrUsless(String source){
        if(source.startsWith("86")){
            return source.substring(2,source.length());
        }else if(source.startsWith("086")){
            return source.substring(3);
        } else{
            return source;
        }
    }
    public static String numberOnly(String source){
        String regex = "[^0-9]";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(source);
        return matcher.replaceAll("");
    }
    public static boolean isCellPhone(PhoneNumber phoneNumber){
        String source = phoneNumber.getNumbers();
        source = substrUsless(source);
        source=numberOnly(source);
        Pattern p = Pattern.compile(CELLPHONE);
        Matcher m =p.matcher(source);
        return m.find();
    }
    public static boolean isTelPhone(PhoneNumber phoneNumber){
        String source = phoneNumber.getNumbers();
        source = substrUsless(source);
        source=numberOnly(source);
        Pattern p = Pattern.compile(TELPHONE);
        Matcher m =p.matcher(source);
        return m.find();
    }
    //电话号码中间添加空格
    public static String formatString(String source){
        source = numberOnly(source);
        int length = source.length();
        int firstLeg = length % 4;
        int times = length / 4;
        String space = " ";
        StringBuffer returnStr = new StringBuffer(source.substring(0,firstLeg));
        for(int i = 0;i < times;i++){
            returnStr.append(space + source.substring(i*4 + firstLeg,(i+1)*4 + firstLeg));
        }
        return returnStr.toString();
    }
    public static void main(String[] args) throws ParseException {
        String phone = "+8618626344168";
        String phone1 = "+860375-45366522";
        String phone2 = "123 1234 2736";
        //println(formatString("12343520567"));
        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setNumbers(phone);
        println(isCellPhone(phoneNumber));


        PhoneNumber phoneNumber1 = new PhoneNumber();
        phoneNumber1.setNumbers(phone1);
        println(isTelPhone(phoneNumber1));
        PhoneNumbersFormat pnf = new PhoneNumbersFormat();
        PhoneNumber phoneNumber2 = pnf.parse(phone,Locale.CHINA);
        PhoneNumber phoneNumber3 = pnf.parse(phone1,Locale.CHINA);
        println(pnf.print(phoneNumber2,Locale.CHINA));
        println(pnf.print(phoneNumber3,Locale.CHINA));
        println(isCellPhone(phoneNumber1));
        println(phone + "是不是固话" + isCellPhone(phoneNumber2));
        println(phone1 + "是不是固话" +isTelPhone(phoneNumber3));
    }

}
