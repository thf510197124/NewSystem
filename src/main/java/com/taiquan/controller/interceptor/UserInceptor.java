package com.taiquan.controller.interceptor;

import com.taiquan.domain.users.User;
import com.taiquan.utils.DESUtils;
import com.taiquan.utils.LimitQueue;
import com.taiquan.utils.MyHttpClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Logger;

import static com.taiquan.utils.PrintUtil.println;

//Interceptor 不管用，主要是因为Post方法经过拦截，变成了get方法，可以说，只能用于拦截GET方法
//两个拦截器一起使用的话，发生的过程是inceptor ---》 handler ----》 inceptor ，而不是inceptor-》inceptor-》handler
public class UserInceptor implements HandlerInterceptor {
    /*private Logger logger = Logger.getLogger(UserInceptor.class);*/
    private Queue<String> urlQueue = new LimitQueue<>(10);
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        String contextPath= httpServletRequest.getContextPath();
        int conIndex = url.indexOf(contextPath) + contextPath.length();
        String beforUrl = url.substring(conIndex);
        if(url.contains("login")){
            return true;
        }
        String username = (String) httpServletRequest.getSession().getAttribute("username");
        if (username != null){
            return true;
        }
        //查看cookie中有没有过期
        Cookie[] cookies = httpServletRequest.getCookies();
        User user = new User();
        if (cookies != null){
            for (Cookie cookie : cookies){
                switch (cookie.getName()) {
                    case "username":
                        user.setUsername(DESUtils.getDecryptString(cookie.getValue()));
                        break;
                    case "password":
                        user.setPassword(DESUtils.getDecryptString(cookie.getValue()));
                        break;
                    case "mgr":
                        user.setMgr(Boolean.getBoolean(cookie.getValue()));
                        break;
                }
            }
        }
        if (user.getUsername() != null && user.getPassword() != null){
            httpServletRequest.getSession().setAttribute("user",user);
            httpServletRequest.getSession().setAttribute("username",user.getUsername());
            return true;
        }
        httpServletRequest.getSession().setAttribute("beforeUrl",beforUrl);
        httpServletResponse.sendRedirect(contextPath+"/user/login.html");
        return false;
        /*httpServletRequest.getSession().setAttribute("beforeUrl",beforUrl);
        if (httpServletRequest.getMethod().equalsIgnoreCase("post")){
            Map<String,String[]> paraMap1 = httpServletRequest.getParameterMap();
            Map<String,String[]> paramMap = new HashMap<>();
            for (Map.Entry<String,String[]> param : paraMap1.entrySet()){
                paramMap.put(param.getKey(),param.getValue());
            }
            httpServletRequest.getSession().setAttribute("paramMap",paramMap);
        }
        httpServletResponse.sendRedirect(contextPath+"/user/login.html");
        return false;*/
    }

    //postHandle是已经通过controller，只能对request进行处理
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
