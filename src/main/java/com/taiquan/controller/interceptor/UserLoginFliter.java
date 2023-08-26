package com.taiquan.controller.interceptor;


import com.taiquan.utils.MyHttpClient;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.taiquan.utils.PrintUtil.println;

public class UserLoginFliter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        String contextPath= request.getContextPath();
        int conIndex = url.indexOf(contextPath) + contextPath.length();
        if(url.contains("login")){
            filterChain.doFilter(request,response);
            return;
        }
        String username = (String) request.getSession().getAttribute("username");
        if (username != null){
            println("UserName is not null ######################################################");
            //是否要处理经过login处理的页面？
            if (request.getSession().getAttribute("boforeUrl") != null){
                println(request.getSession().getAttribute("boforeUrl"));
                String thisUrl = url.substring(0,url.lastIndexOf("."));
                println("######################################################################");
                println(url);
                println(thisUrl);
                println("######################################################################");
                Map<String,String[]> paraMap = (Map<String,String[]>)request.getSession().getAttribute("paraMap");
                request.getSession().removeAttribute("boforeUrl");
                request.removeAttribute("paraMap");
                MyHttpClient httpClient = new MyHttpClient(response);
                httpClient.setParamMap(paraMap);
                httpClient.sendByPost(thisUrl);
            }else {
                filterChain.doFilter(request, response);
            }
            return;
        }
        //对于Post请求的处理
        request.getSession().setAttribute("beforeUrl",url.substring(conIndex));
        println("set beforeUrl  ######################################################");
        Map<String,String[]> paraMap = request.getParameterMap();
        if (!paraMap.isEmpty()){
            request.getSession().setAttribute("paramMap",paraMap);
        }
        response.sendRedirect(contextPath+"/user/login.html");
        return;
    }

    @Override
    public void destroy() {

    }
}
