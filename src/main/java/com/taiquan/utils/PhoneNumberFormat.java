package com.taiquan.utils;

import com.taiquan.domain.customer.Address;
import com.taiquan.domain.customer.PhoneNumber;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.taiquan.utils.PrintUtil.println;

/***
 * 处理电话号码，把没有区号的号码，变成有去好的号码
 */
public class PhoneNumberFormat {
    public static final Map<String,String> REGION = new HashMap<String,String>();
    static {
        //File file = new File("/src/main/Resources/region.txt");
        Resource file = new ClassPathResource("region.txt");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line = null;
            while (reader.readLine() != null){
                line = reader.readLine();
                String[] linesArray = line.split(" ");
                if (linesArray.length == 2){
                    REGION.put(linesArray[0],linesArray[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<PhoneNumber> formatPhone(String city,List<String> phones){
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for (String phone : phones) {
            if (!phone.startsWith("0") && !phone.startsWith("1") && phone.length() < 8) {
                phone = REGION.get(city) + phone;
            }
            phoneNumbers.add(new PhoneNumber(phone));
        }
        return phoneNumbers;
    }
    public static List<PhoneNumber> formatPhone(Address address,List<String> phones){
        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        for (String phone : phones){
            if (!phone.startsWith("0") && !phone.startsWith("1") && phone.length() < 8){
                if (address.getFormatterAddress() != null
                        && !address.getFormatterAddress().equals("")
                        && address.getFormatterAddress().length() != 0){
                    String formatted = address.getFormatterAddress();
                    int indexSheng = formatted.indexOf("省");
                    int indexCity = formatted.indexOf("市");
                    if (indexCity > 0){
                        if (indexSheng > 0) {
                            phone = REGION.get(formatted.substring(indexSheng + 1, indexCity)) + phone;
                        }else{
                            phone = REGION.get(formatted.substring(indexCity-2,indexCity)) + phone;
                        }
                    }
                }
            }
            phoneNumbers.add(new PhoneNumber(phone));
        }
        return phoneNumbers;
    }
    public static void main(String[] args){
        println(REGION);
    }

}
