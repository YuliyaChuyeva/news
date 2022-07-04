package com.example.news.web.controller;


import com.example.news.dao.model.Users;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {



    @GetMapping("/login")
    public String getLoginPage(){
        return "loginPage";
    }
}
