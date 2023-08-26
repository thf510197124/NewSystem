package com.taiquan.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.ConstructorResult;

@Controller
public class ErrorController {
    @RequestMapping(value = "*")
    public ModelAndView notFound(){
        ModelAndView modelMap = new ModelAndView();
        modelMap.addObject("error","没找到相应页面");
        modelMap.setViewName("/404");
        return modelMap;
    }
}
