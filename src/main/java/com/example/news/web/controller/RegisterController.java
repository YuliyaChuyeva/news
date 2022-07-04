package com.example.news.web.controller;

import com.example.news.dao.model.Role;
import com.example.news.dao.model.Users;
import com.example.news.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private  final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("userReg")
    public Users getEmptyUser(){
        return new Users();
    }

    @GetMapping("/register")
    public String getRegisterPage(){
        return "registerPage";
    }

    @PostMapping("/userRegister")
    public String registerUser(@Valid Users user, Errors errors){
        if(errors.hasErrors())
            return "registerPage";

        user.setRole(Role.ROLE_USER);
        userService.save(user);
        return "loginPage";
    }
}
