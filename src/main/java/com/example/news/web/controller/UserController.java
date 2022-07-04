package com.example.news.web.controller;


import com.example.news.dao.model.Users;
import com.example.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all/users")
    public String getAllPerson(Model model){
        List<Users> allUsers = userService.findAll();
        model.addAttribute("users",allUsers);
        return "firstPage";
    }

    @GetMapping("/user/{id}")
    public String getUserById(Model model, @PathVariable("id") int id){
    Users user = userService.findUsersById(id);
    model.addAttribute("someUser",user);
    return "firstPage";
    }


}
