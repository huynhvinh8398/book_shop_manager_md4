package com.cg.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController {

    @GetMapping("/homepage")
    public ModelAndView showPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/homepage/list");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/login");
        return modelAndView;

    }
    @GetMapping("/products")
    public ModelAndView showPageProduct(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/product/listProduct");
        return modelAndView;
    }
    @GetMapping("/users")
    public ModelAndView showPageUser(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/user/listUser");
        return modelAndView;
    }
}
