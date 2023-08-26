package com.taiquan.utils;

public class JSONUtils {
    public static String removeQuot(String jsonStr){
        if(jsonStr.startsWith("\"")){
            return jsonStr.trim().substring(1,jsonStr.length()-1);
            //传进来的String 包含了前后引号
        }else {
            return jsonStr;
        }
    }
}
