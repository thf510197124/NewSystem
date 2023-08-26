package com.taiquan.controller.interceptor;

import com.taiquan.utils.MyHttpClient;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import static com.taiquan.utils.PrintUtil.println;

public class AfterInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*println("Come to Second Inteceptor-------------------------------------------------------");
        String url = (String)request.getSession().getAttribute("beforeUrl");
        String thisUrl = url.substring(0,url.lastIndexOf("."));
        println("######################################################################");
        println(url);
        println(thisUrl);
        println("######################################################################");
        Map<String,String[]> paraMap = (Map<String,String[]>)request.getSession().getAttribute("paraMap");
        if (paraMap != null){
            println("***********************************************************************************************************");
            request.getSession().removeAttribute("");
            MyHttpClient httpClient = new MyHttpClient(response);
            httpClient.setParamMap(paraMap);
            httpClient.sendByPost(thisUrl);
        }*/
        //不用拦截
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        println("Come to AfterInteceptor-------------------------------------------------------");
        String url = (String)request.getSession().getAttribute("beforeUrl");
        if (url!=null){
            Map<String,String[]> paraMap = (Map<String,String[]>)request.getSession().getAttribute("paraMap");
            if (paraMap !=null){
                MyHttpClient httpClient = new MyHttpClient(response);
                httpClient.setParamMap(paraMap);
                httpClient.sendByPost(url);
            }
            else {
                modelAndView.setViewName(url);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
