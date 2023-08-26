package com.taiquan.utils;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.taiquan.utils.PrintUtil.println;

public class MyHttpClient {
    Map<String,String[]> paramMap = new HashMap<String,String[]>();
    HttpServletResponse response;
    public MyHttpClient(HttpServletResponse response){
        this.response = response;
    }

    public void setParamMap(Map<String, String[]> paramMap) {
        this.paramMap = paramMap;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void sendByPost(String url) throws IOException{
        this.response.setContentType("text/html");
        PrintWriter out = this.response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><title>sender</title></head>");
        out.println("<body>");
        out.println("<form name=\"submitForm\" action=\"" + url + "\" method=\"post\">");
        Iterator<String> it = paramMap.keySet().iterator();
        while(it.hasNext()){
            String key =it.next();
            out.println("<input type=\"hidden\" name = \"" + key + "\"  value = \"" + paramMap.get(key)[0] + "\"/>");
        }
        out.println("</form>");
        out.println("<script>window.document.submitForm.submit();</script>");
        out.println("</body>");
        out.println("</html>");
        out.flush();
        out.close();
        println("********************************************************************************");
        println("use my HttpClient");
        println("********************************************************************************");
    }
}
