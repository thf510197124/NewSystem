package com.taiquan.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.taiquan.utils.PrintUtil.println;

@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext != null){
            SpringUtils.applicationContext = applicationContext;
        }
    }
    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }
    public static Object getBean(String name){
        println("##################################################################################################################");
        println(getApplicationContext().getApplicationName());
        println(name);
        println("###################################################################################################################");
        return getApplicationContext().getBean(name);
    }
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }
    public static <T> T getBean(String name,Class<T> tClass){
        return getApplicationContext().getBean(name,tClass);
    }
    public static <T> Map<String,T> getBeansByType(Class<T> tClass){
            return getApplicationContext().getBeansOfType(tClass);
    }
    public static String getProperty(String name){
        Environment env = applicationContext.getEnvironment();
        return env.getProperty(name);
    }
}
