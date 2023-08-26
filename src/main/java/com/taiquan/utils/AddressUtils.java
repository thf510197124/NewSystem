package com.taiquan.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taiquan.domain.customer.Address;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static com.taiquan.utils.PrintUtil.println;

public class AddressUtils {

    public static Corderinate getGeocoderAngel(String address){
        Corderinate corderinate = new Corderinate();
        BufferedReader in = null;
        try {
            address = URLEncoder.encode(address, "UTF-8");
            URL tirc = new URL("http://api.map.baidu.com/geocoder?address="+ address +"&output=json&key="+"7d9fbeb43e975cd1e9477a7e5d5e192a");
            in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while((res = in.readLine())!=null){
                sb.append(res.trim());
            }
            String str = sb.toString();
            if(StringUtils.isNotEmpty(str)){
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if(lngStart > 0 && lngEnd > 0 && latEnd > 0){
                    String lng = str.substring(lngStart+5, lngEnd);
                    String lat = str.substring(lngEnd+7, latEnd);
                    corderinate.setLng(new BigDecimal(lng,new MathContext(10, RoundingMode.FLOOR)));
                    corderinate.setLat(new BigDecimal(lat,new MathContext(10, RoundingMode.FLOOR)));
                    return corderinate;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                println("---------------------------------------------------------------------");
                println("Bad Address");
               e.printStackTrace();
            }
        }
        return corderinate;
    }

    public static JsonNode getposition(String longitude,String latitude) throws MalformedURLException {
        BufferedReader in   = null;
        URL            tirc = new URL("http://api.map.baidu.com/geocoder?location="+ latitude+","+longitude+"&output=json&key="+"E4805d16520de693a3fe707cdc962045");
        try {
            in = new BufferedReader(new InputStreamReader(tirc.openStream(),"UTF-8"));
            String res;
            StringBuilder sb = new StringBuilder("");
            while((res = in.readLine())!=null){
                sb.append(res.trim());
            }
            String str = sb.toString();
            //System.out.println(str);
            ObjectMapper mapper = new ObjectMapper();
            if(StringUtils.isNotEmpty(str)){
                JsonNode jsonNode = mapper.readTree(str);
                jsonNode.findValue("status").toString();
                JsonNode resultNode = jsonNode.findValue("result");
                return  resultNode;
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param address
     * @return if formatAddress success,return it.else return null,which signed is failed;
     */
    public static String formatAddress(String address){
        Corderinate position = AddressUtils.getGeocoderAngel(address);
        if(position != null) {
            try{
                JsonNode jsonNode = AddressUtils.getposition(position.getLng().toString(),position.getLat().toPlainString());
                JsonNode locationNode = jsonNode.findValue("formatted_address");
                return locationNode.toString();
            }catch (Exception e){
            }
        }
        return address+"   地址缺陷";
    }
    public static Address createAddress(String add){
        Address address = new Address();
        if (add.equals("")){
            return null;
        }
        address.setSimple(add);
        address.setFormatterAddress(formatAddress(add));
        if (getGeocoderAngel(add)!= null) {
            address.setLongitude(getGeocoderAngel(add).getLng());
            address.setLatitude(getGeocoderAngel(add).getLat());
        }
        return address;
    }

    public static void main(String[] args) throws MalformedURLException {
        Corderinate co = getGeocoderAngel("江苏省无锡市锡山区北环路108号");
        System.out.println("经度是：" + co.getLng().toString() +"纬度是：" + co.getLat().toString());
//        System.out.println(getposition("120.305456","44.570037"));
        JsonNode jsonNode = AddressUtils.getposition("120.338854","31.628027");
        System.out.println("jsonNode = " + jsonNode);

        System.out.println(formatAddress("锡山区北环路108号"));
    }


}
