package com.taiquan.controller.customer;

import com.taiquan.utils.PageUtil;

import javax.servlet.http.HttpSession;

public class WebUtils {
    public static int getPageNo(HttpSession session){
        try{
            return (int) session.getAttribute("pageNo");
        }catch (Exception e){
            return 1;
        }
    }
    public static int getPageSize(HttpSession session){
        try{
            return (int) session.getAttribute("pageSize");
        }catch (Exception e){
            return PageUtil.PAGE_SIZE;
        }
    }
    public static String trimJsonStr(String jsonStr){
        if (jsonStr.startsWith("\"")){
            jsonStr=jsonStr.substring(1,jsonStr.length());
        }
        if (jsonStr.endsWith("\"")){
            jsonStr = jsonStr.substring(0,jsonStr.length() -1);
        }
        return jsonStr;
    }
}
